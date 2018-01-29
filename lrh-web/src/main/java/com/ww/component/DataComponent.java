package com.ww.component;

import com.ww.domain.*;
import com.ww.service.DataBaseService;
import com.ww.util.DataUtil;
import com.ww.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/21.
 */
@Service
public class DataComponent {
    private final Logger logger = Logger.getLogger(DataComponent.class);
    @Resource
    private DataBaseService dataBaseService;
    @Value("${payItem.itemsPerPage}")
    private int itemsPerPage;
    @Value("${personal.photo.path}")
    private String personalPhotoPath;
    public ModelAndView data_dataList(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("data_list");
        return view;
    }

    public ModelAndView payItemList(HttpServletRequest request) {
        int crruntPage = request.getParameter("crruntPage")==null?1:(Integer.valueOf((String)(request.getParameter("crruntPage"))));

        int totalCounts = dataBaseService.getPayItemTotalCounts();
        //总记录条数
        logger.info("totalCounts="+totalCounts);
        int totalPages= DataUtil.getTotalPages(totalCounts,itemsPerPage);

        //获取总共的页数
        ModelAndView view = new ModelAndView("payItemList");
        int start=(crruntPage-1)*itemsPerPage;
        int end=itemsPerPage;
        DataForPage dataForPage = new DataForPage(start,end);
        //分页查询对象
        List<PayItem> payItemListLimit=dataBaseService.getPayItemListLimit(dataForPage);
        view.addObject("payItemListLimit",payItemListLimit);
        view.addObject("crruntPage",crruntPage);
        view.addObject("totalPages",totalPages);
        //当前页面
        logger.info("crruntPage="+crruntPage);
        logger.info("totalPages="+totalPages);
        return view;
    }

    public ModelAndView paySubitemList(HttpServletRequest request) {
        ModelAndView view = new ModelAndView();
        int crruntPage = request.getParameter("crruntPage")==null?1:(Integer.valueOf((String)(request.getParameter("crruntPage"))));

        String itemName=(String)request.getParameter("itemName");
        logger.info("paySubitemList.itemName="+itemName);
        //可能为空
        if(StringUtil.stringIsBlank(itemName)){
            view.setViewName("paySubitemList");
        }else{
            int totalCounts = dataBaseService.getPaySubitemTotalCountsByItemName(itemName);
            //总记录条数
            logger.info("totalCounts="+totalCounts);
            int totalPages= DataUtil.getTotalPages(totalCounts,itemsPerPage);

            //获取总共的页数

            int start=(crruntPage-1)*itemsPerPage;
            int end=itemsPerPage;
            PaySubitemDataForPage paySubitemDataForPage = new PaySubitemDataForPage(start,end,itemName);
            //分页查询对象
            List<PaySubitem> paySubitemListLimit=dataBaseService.selectPaySubitemListLimitByItemName(paySubitemDataForPage);
            view.addObject("paySubitemListLimit",paySubitemListLimit);
            view.addObject("crruntPage",crruntPage);
            view.addObject("totalPages",totalPages);
            view.addObject("itemName",itemName);
            //当前页面
            logger.info("crruntPage="+crruntPage);
            logger.info("totalPages="+totalPages);
            view.setViewName("paySubitemList");
        }
        return view;
    }



    public Map<String,Object> itemNameExists(String itemName) {
        String checkItemNameExistMessage="";
        Map<String,Object> data = new HashMap<String,Object>();
        PayItem payItemExists = dataBaseService.selectPayItemByItemName(itemName);
        if(payItemExists==null){
            checkItemNameExistMessage="项目名称可用";
        }else{
            checkItemNameExistMessage="项目名称已存在，请更换一个";
        }
        data.put("checkItemNameExistMessage", checkItemNameExistMessage);
        return data;
    }

    //插入数据
    public ModelAndView addPayItem(PayItem payItem) throws UnsupportedEncodingException {
        ModelAndView view = new ModelAndView();
        String message="";
        if(StringUtil.stringIsBlank(payItem.getItemName())){
            view.setViewName("redirect:/dataManage/toPayItemAdd");
            message="项目不能为空";
            view.addObject("message",URLEncoder.encode(message,"UTF-8"));
            return view;
        }
        String itemName = payItem.getItemName();
        PayItem itemNameExist=dataBaseService.selectPayItemByItemName(itemName);
        if(itemNameExist==null){
            //不存在则插入
            dataBaseService.insertPayItemSelective(payItem);
            //插入成功后跳转到展示页面
            //直接跳转到登录页面
            view.setViewName("redirect:/dataManage/payItemList");
            return view;
        }else{
            //存在的提示已存在
            view.setViewName("redirect:/dataManage/toPayItemAdd");
            message="项目已存在";
            view.addObject("message",URLEncoder.encode(message,"UTF-8"));
            return view;
        }

    }

    public ModelAndView deletePayItem(Long itemId) {
        dataBaseService.deleteByPrimaryKey(itemId);
        ModelAndView view = new ModelAndView();
        String message="";
        view.setViewName("redirect:/dataManage/payItemList");
        return view;
    }

    public ModelAndView toPayItemAdd(HttpServletRequest request) throws UnsupportedEncodingException {
        ModelAndView view = new ModelAndView("addPayItem");
        if(request.getParameter("message")!=null){
            view.addObject("message",URLDecoder.decode(request.getParameter("message"),"UTF-8"));
        }
        //参数传值
        return view;
    }

    public ModelAndView toPayItemModify(Long itemId,HttpServletRequest request) throws UnsupportedEncodingException {
        PayItem payItem = dataBaseService.selectPayItemByPrimaryKey(itemId);
        ModelAndView view = new ModelAndView("payItemModify");
        view.addObject("payItem",payItem);
        if(request.getParameter("message")!=null){
            view.addObject("message",URLDecoder.decode(request.getParameter("message"),"UTF-8"));
        }
        return view;
    }

    public ModelAndView payItemModify(PayItem payItem) throws UnsupportedEncodingException {
        //先判断新修改的记录在数据库中是否存在，不存在才允许修改
        ModelAndView view = new ModelAndView();
        String message="";
        if(StringUtil.stringIsBlank(payItem.getItemName())){
            message="项目不能为空";
            return modifyError(message,payItem.getItemId());
        }
        PayItem payItemExists=dataBaseService.selectPayItemByItemName(payItem.getItemName());
        if(payItemExists!=null){
            message="项目已存在";
            return modifyError(message, payItem.getItemId());
        }
        dataBaseService.updatePayItemSelective(payItem);
         view.setViewName("redirect:/dataManage/payItemList");
        return view;
    }

    private ModelAndView modifyError(String message, Long itemId) throws UnsupportedEncodingException {
        ModelAndView view = new ModelAndView();
        view.setViewName("redirect:/dataManage/toPayItemModify");
        view.addObject("message",URLEncoder.encode(message,"UTF-8"));
        view.addObject("itemId",itemId);
        return view;
    }

    public ModelAndView toPaySubitemList(HttpServletRequest request) {
        ModelAndView view = new ModelAndView();
        int crruntPage = request.getParameter("crruntPage")==null?1:(Integer.valueOf((String)(request.getParameter("crruntPage"))));

        String itemName=(String)request.getParameter("itemName");
        logger.info("paySubitemList.itemName="+itemName);
        //可能为空
        if(StringUtil.stringIsBlank(itemName)){
            List<PayItem> list = dataBaseService.selectPayItemListAll();
            if(list!=null &&list.size()>0){
                itemName=list.get(0).getItemName();
            }else{
                view.setViewName("paySubitemList");
                return view;
            }
        }
        int totalCounts = dataBaseService.getPaySubitemTotalCountsByItemName(itemName);
        //总记录条数
        logger.info("totalCounts="+totalCounts);
        int totalPages= DataUtil.getTotalPages(totalCounts,itemsPerPage);
        //获取总共的页数
        int start=(crruntPage-1)*itemsPerPage;
        int end=start+itemsPerPage;
        PaySubitemDataForPage paySubitemDataForPage = new PaySubitemDataForPage(start,end,itemName);
        //分页查询对象
        List<PaySubitem> paySubitemListLimit=dataBaseService.selectPaySubitemListLimitByItemName(paySubitemDataForPage);
        view.addObject("paySubitemListLimit",paySubitemListLimit);
        view.addObject("crruntPage",crruntPage);
        view.addObject("totalPages",totalPages);
        view.addObject("itemName",itemName);

        //当前页面
        logger.debug("crruntPage="+crruntPage);
        logger.debug("totalPages="+totalPages);
        view.setViewName("paySubitemList");
        return view;
    }

    public List<PayItem> payItemListAll() {
        return dataBaseService.selectPayItemListAll();
    }

    public ModelAndView deletePaySubitem(Long subitemId, String itemName) {
        dataBaseService.deletePaySubitemByPrimaryKey(subitemId);
        ModelAndView view = new ModelAndView();
        String message="";
        view.addObject("itemName",itemName);
        view.setViewName("redirect:/dataManage/paySubitemList");
        return view;
    }

    public ModelAndView toPaySunitemModify(Long subitemId, String itemName, HttpServletRequest request) throws UnsupportedEncodingException {
        PaySubitem paySubitem = dataBaseService.selectPaySubitemByPrimaryKey(subitemId);
        ModelAndView view = new ModelAndView("paySubitemModify");
        view.addObject("paySubitem",paySubitem);
        if(request.getParameter("message")!=null){
            view.addObject("message",URLDecoder.decode(request.getParameter("message"),"UTF-8"));
        }
        return view;
    }

    public ModelAndView paySubitemModify(PaySubitem paySubitem) throws UnsupportedEncodingException {
        //先判断新修改的记录在数据库中是否存在，不存在才允许修改
        ModelAndView view = new ModelAndView();
        String itemName = paySubitem.getItemName();
        String message="";
        if(StringUtil.stringIsBlank(paySubitem.getSubitemName())){
            message="项目不能为空";
            return modifyErrorSubitem(message,paySubitem);
        }
        PaySubitem paySubitemExists=dataBaseService.selectPaySubitemByPrimaryKey(paySubitem.getSubitemId());
        //根据 itemName和subitemName查询
        if(paySubitemExists.getSubitemName().equals(paySubitem.getSubitemName())){
            message="项目已存在";
            return modifyErrorSubitem(message,paySubitem);
        }
        dataBaseService.updatePaySubitemSelective(paySubitem);
        view.addObject("itemName",itemName);
        view.setViewName("redirect:/dataManage/toPaySubitemList");
        return view;
    }

    private ModelAndView modifyErrorSubitem(String message, PaySubitem paySubitem) throws UnsupportedEncodingException {
        ModelAndView view = new ModelAndView();
        view.setViewName("redirect:/dataManage/toPaySunitemModify");
        view.addObject("message",URLEncoder.encode(message,"UTF-8"));
        view.addObject("subitemId",paySubitem.getSubitemId());
        view.addObject("itemName",paySubitem.getItemName());
        return view;
    }

    public ModelAndView toPaySubitemAdd(String itemNameOld, HttpServletRequest request) throws UnsupportedEncodingException {
        ModelAndView view = new ModelAndView("paySubitemAdd");
        view.addObject("itemNameOld",itemNameOld);
        logger.info("itemNameOld="+itemNameOld);
        if(request.getParameter("message")!=null){
            view.addObject("message",URLDecoder.decode(request.getParameter("message"),"UTF-8"));
        }
        //参数传值
        return view;
    }

    public Map<String,Object> paySubitemExists(String itemName, String subitemName) {
        boolean alreadyExist=false;
        //默认是不存在
        String checkPaySubitemExistMessage="项目名称可用";
        PaySubitem paySubitem=new PaySubitem(itemName,subitemName);
        PaySubitem paySubitemExist=dataBaseService.selectPaySubitemByItemNameAndSubitemName(paySubitem);
        if(paySubitemExist!=null){
            alreadyExist=true;
            checkPaySubitemExistMessage="项目已存在，请换一个或者取消";
        }
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("checkPaySubitemExistMessage", checkPaySubitemExistMessage);
        data.put("alreadyExist", alreadyExist);
        return data;



    }

    public ModelAndView addPaySubitem(PaySubitem paySubitem, String itemNameOld) throws UnsupportedEncodingException {
        ModelAndView view = new ModelAndView();
        String message="";
        if(StringUtil.stringIsBlank(paySubitem.getSubitemName())){
            message="项目不能为空";
            return toPaySubitemAddView(message,itemNameOld);

        }
        dataBaseService.insertPaySubitemSelective(paySubitem);
        //插入成功后跳转到展示页面
        //直接跳转到登录页面
        view.setViewName("redirect:/dataManage/paySubitemList");
        view.addObject("itemName",itemNameOld);
        return view;


    }

    private ModelAndView toPaySubitemAddView(String message, String itemNameOld) throws UnsupportedEncodingException {
        ModelAndView view= new ModelAndView("redirect:/dataManage/toPaySubitemAdd");
        view.addObject("message",URLEncoder.encode(message,"UTF-8"));
        view.addObject("itemNameOld",itemNameOld);
        return view;
    }

    public ModelAndView toPayDetailList(HttpServletRequest request) throws UnsupportedEncodingException {
        PayDetailDataForPage payDetailDataForPage = buildPayDetailDataForPage(request);

        modifyPayDetailDataForPage(payDetailDataForPage);
        int crruntPage = DataUtil.intIsEmpty((String)request.getParameter("crruntPage"))?1:(Integer.valueOf((String)(request.getParameter("crruntPage"))));
        int totalCounts = dataBaseService.selectPayDetailTotalCountsByConditions(payDetailDataForPage);
        //总记录条数
        logger.debug("totalCounts="+totalCounts);
        logger.debug("crruntPage="+crruntPage);

        int totalPages= DataUtil.getTotalPages(totalCounts,itemsPerPage);
        //获取总共的页数
        int startPage=(crruntPage-1)*itemsPerPage;
        payDetailDataForPage.setStartPage(startPage);
        //设置起始页
        payDetailDataForPage.setItemsPerPage(itemsPerPage);
        //设置初始值
        payDetailDataForPage.setCrruntPage(crruntPage);
        //设置当前页数
        //每一页记录条数
        List<PayDetail> payDetailListLimit = dataBaseService.selectPayDetailLimitByConditions(payDetailDataForPage);
        //分页查询对象
        ModelAndView view= new ModelAndView("");
        view.addObject("payDetailListLimit",payDetailListLimit);
        view.addObject("crruntPage",crruntPage);
        view.addObject("totalPages",totalPages);
        modifyNullToBlank(payDetailDataForPage);
        //将null值变成空字符串
        view.addObject("payDetailDataForPage",payDetailDataForPage);
        view.setViewName("payDetailList");
        return view;



    }

    private void modifyNullToBlank(PayDetailDataForPage payDetailDataForPage) throws UnsupportedEncodingException {
        payDetailDataForPage.setItemName(payDetailDataForPage.getItemName()==null?
                "":payDetailDataForPage.getItemName());
        String subitemName = payDetailDataForPage.getSubitemName();
        payDetailDataForPage.setSubitemName(subitemName==null?
                "":subitemName);
        //logger.info("URLEncoder.encode 之后的数据..."+subitemName);
        payDetailDataForPage.setPayPerson(payDetailDataForPage.getPayPerson()==null?
                "":payDetailDataForPage.getPayPerson());
        payDetailDataForPage.setPayDateStart(payDetailDataForPage.getPayDateStart()==null?
                "":payDetailDataForPage.getPayDateStart());
        payDetailDataForPage.setPayDateEnd(payDetailDataForPage.getPayDateEnd()==null?
                "":payDetailDataForPage.getPayDateEnd());
    }

    private PayDetailDataForPage buildPayDetailDataForPage(HttpServletRequest request) throws UnsupportedEncodingException {

        PayDetailDataForPage payDetailDataForPage = new PayDetailDataForPage();
        String oldFlag=(String)request.getParameter("oldFlag");
        //logger.info("====oldFlag="+oldFlag);
        if(StringUtil.stringIsBlank(oldFlag)){
            /*oldFlag 0-查询跳转的页面 1-其他操作跳转的页面*/
            /*payDetailDataForPage.setItemName((String)request.getParameter("itemName"));
            payDetailDataForPage.setSubitemName((String)request.getParameter("subitemName"));
            payDetailDataForPage.setPayPerson((String)request.getParameter("payPerson"));*/

            payDetailDataForPage.setItemName(request.getParameter("itemName")==null?
                    "":URLDecoder.decode(request.getParameter("itemName"),"utf-8"));
            payDetailDataForPage.setSubitemName(request.getParameter("subitemName")==null?
                    "":URLDecoder.decode(request.getParameter("subitemName"),"utf-8"));
            payDetailDataForPage.setPayPerson(request.getParameter("payPerson")==null?
                    "":URLDecoder.decode(request.getParameter("payPerson"),"utf-8"));


            payDetailDataForPage.setPayDateStart((String)request.getParameter("payDateStart"));
            payDetailDataForPage.setPayDateEnd((String)request.getParameter("payDateEnd"));
        }else if("1".equals(oldFlag)){
            payDetailDataForPage.setItemName(request.getParameter("itemNameOld")==null?
                    "":URLDecoder.decode(request.getParameter("itemNameOld"),"utf-8"));
            payDetailDataForPage.setSubitemName(request.getParameter("subitemNameOld")==null?
                    "":URLDecoder.decode(request.getParameter("subitemNameOld"),"utf-8"));
            payDetailDataForPage.setPayPerson(request.getParameter("payPersonOld")==null?
                    "":URLDecoder.decode(request.getParameter("payPersonOld"),"utf-8"));
            payDetailDataForPage.setPayDateStart((String)request.getParameter("payDateStartOld"));
            payDetailDataForPage.setPayDateEnd((String)request.getParameter("payDateEndOld"));
        }else if("delete".equals(oldFlag)){
            String itemName=request.getParameter("itemName")==null?
                    "":URLDecoder.decode(request.getParameter("itemName"),"utf-8");
            payDetailDataForPage.setItemName(itemName);
            String subitemName=request.getParameter("subitemName")==null?
                        "":URLDecoder.decode(request.getParameter("subitemName"),"utf-8");
            payDetailDataForPage.setSubitemName(subitemName);
            String payPerson = request.getParameter("payPerson")==null?
                    "":URLDecoder.decode(request.getParameter("payPerson"),"utf-8");
            payDetailDataForPage.setPayPerson(payPerson);
            payDetailDataForPage.setPayDateStart((String)request.getParameter("payDateStart"));
            payDetailDataForPage.setPayDateEnd((String)request.getParameter("payDateEnd"));
        }

        return payDetailDataForPage;
    }

    private void modifyPayDetailDataForPage(PayDetailDataForPage payDetailDataForPage) {

        payDetailDataForPage.setItemName(StringUtil.stringIsBlank(payDetailDataForPage.getItemName())
                ?null:payDetailDataForPage.getItemName());
        payDetailDataForPage.setSubitemName(StringUtil.stringIsBlank(payDetailDataForPage.getSubitemName())
                ?null:payDetailDataForPage.getSubitemName());

        payDetailDataForPage.setPayPerson(StringUtil.stringIsBlank(payDetailDataForPage.getPayPerson())
                ?(null):payDetailDataForPage.getPayPerson());
        payDetailDataForPage.setPayDateStart(StringUtil.stringIsBlank(payDetailDataForPage.getPayDateStart())
                ?null:payDetailDataForPage.getPayDateStart());
        payDetailDataForPage.setPayDateEnd(StringUtil.stringIsBlank(payDetailDataForPage.getPayDateEnd())
                ?null:payDetailDataForPage.getPayDateEnd());
    }

    public ModelAndView toPayDetailAdd(HttpServletRequest request) {
        ModelAndView view= new ModelAndView("");
        String itemNameOld = (String)request.getParameter("itemNameOld");
        String subitemNameOld = (String)request.getParameter("subitemNameOld");
        String payPersonOld = (String)request.getParameter("payPersonOld");
        String payDateStartOld = (String)request.getParameter("payDateStartOld");
        String payDateEndOld = (String)request.getParameter("payDateEndOld");
        String crruntPage = (String)request.getParameter("crruntPage");
        view.addObject("itemNameOld",itemNameOld);
        view.addObject("subitemNameOld",subitemNameOld);
        view.addObject("payPersonOld",payPersonOld);
        view.addObject("payDateStartOld",payDateStartOld);
        view.addObject("payDateEndOld",payDateEndOld);
        view.addObject("crruntPage",crruntPage);
        view.setViewName("payDetailAdd");
        return view;
    }

    public List<PaySubitem> getPaySubitemsByItemName(HttpServletRequest request) {
        String itemName = request.getParameter("itemName");

        if("itemNameInit".equals(itemName)){
            List<PayItem> payItemList = payItemListAll();
            if(payItemList!=null && payItemList.size()>0) itemName=payItemList.get(0).getItemName();
        }
        List<PaySubitem> paySubitemList = dataBaseService.selectPaySubitemsByItemName(itemName);
        return  paySubitemList;
    }

    public List<PaySubitem> selectPayPersonListAll(HttpServletRequest request) {
        return dataBaseService.selectPayPersonListAll();
    }


    //修改
    public ModelAndView modifyPayDetail(PayDetail payDetail, HttpServletRequest request) throws UnsupportedEncodingException {
        ModelAndView view = new ModelAndView();
        String oldFlag = (String)request.getParameter("oldFlag");
        String itemNameOld = (String)request.getParameter("itemNameOld");
        String subitemNameOld = (String)request.getParameter("subitemNameOld");
        String payPersonOld = (String)request.getParameter("payPersonOld");
        String payDateStartOld = (String)request.getParameter("payDateStartOld");
        String payDateEndOld = (String)request.getParameter("payDateEndOld");
        String crruntPage = (String)request.getParameter("crruntPage");
        String message="";
        //不存在则插入
        dataBaseService.updatePayDetailByPrimaryKeySelective(payDetail);
        //插入成功后跳转到展示页面
        view.setViewName("redirect:/payDetail/toPayDetailList");
        view.addObject("oldFlag",oldFlag);
        view.addObject("itemNameOld",itemNameOld==null?itemNameOld:URLEncoder.encode(itemNameOld,"utf-8"));
        view.addObject("subitemNameOld",subitemNameOld==null?subitemNameOld:URLEncoder.encode(subitemNameOld,"utf-8"));
        view.addObject("payPersonOld",payPersonOld==null?payPersonOld:URLEncoder.encode(payPersonOld,"utf-8"));

        view.addObject("payDateStartOld",payDateStartOld);
        view.addObject("payDateEndOld",payDateEndOld);
        view.addObject("crruntPage",crruntPage);
        return view;
    }


    //插入 payDetail
    public ModelAndView addPayDetail(PayDetail payDetail, HttpServletRequest request) throws UnsupportedEncodingException {
        ModelAndView view = new ModelAndView();
        String oldFlag = (String)request.getParameter("oldFlag");
        String itemNameOld = (String)request.getParameter("itemNameOld");
        String subitemNameOld = (String)request.getParameter("subitemNameOld");
        String payPersonOld = (String)request.getParameter("payPersonOld");
        String payDateStartOld = (String)request.getParameter("payDateStartOld");
        String payDateEndOld = (String)request.getParameter("payDateEndOld");
        String crruntPage = (String)request.getParameter("crruntPage");
        String message="";
        //不存在则插入
        dataBaseService.insertPayDetailSelective(payDetail);
        //插入成功后跳转到展示页面
        view.setViewName("redirect:/payDetail/toPayDetailList");
        view.addObject("oldFlag",oldFlag);
        view.addObject("itemNameOld",itemNameOld==null?itemNameOld:URLEncoder.encode(itemNameOld,"utf-8"));
        view.addObject("subitemNameOld",subitemNameOld==null?subitemNameOld:URLEncoder.encode(subitemNameOld,"utf-8"));
        view.addObject("payPersonOld",payPersonOld==null?payPersonOld:URLEncoder.encode(payPersonOld,"utf-8"));

        /*view.addObject("itemNameOld",itemNameOld);
        view.addObject("subitemNameOld",subitemNameOld);
        view.addObject("payPersonOld",payPersonOld);*/
        view.addObject("payDateStartOld",payDateStartOld);
        view.addObject("payDateEndOld",payDateEndOld);
        view.addObject("crruntPage",crruntPage);
        return view;
    }


    //转到修改项目的页面
    public ModelAndView toModifyPayDetail(HttpServletRequest request, long payId) throws UnsupportedEncodingException {
        ModelAndView view = new ModelAndView();
        //查询出项目传递给前段修改
        PayDetail payDetail = dataBaseService.selectPayDetailByPrimaryKey(payId);
        //view.addObject("payDetail",(String)request.getParameter("payDetail"));
        view.addObject("payDetail",payDetail);
        //传值给页面
        //把需要转码的数据转码，然后传递给页面
        String itemName=request.getParameter("itemName")==null?request.getParameter("itemName"):URLDecoder.decode(request.getParameter("itemName"),"utf-8");
        view.addObject("itemName",itemName);
        String subitemName=request.getParameter("subitemName")==null?request.getParameter("subitemName"):URLDecoder.decode(request.getParameter("subitemName"),"utf-8");
        view.addObject("subitemName",subitemName);
        String payPerson=request.getParameter("payPerson")==null?request.getParameter("payPerson"):URLDecoder.decode(request.getParameter("payPerson"),"utf-8");
        view.addObject("payPerson",payPerson);
        view.addObject("payDateStart",(String)request.getParameter("payDateStart"));
        view.addObject("payDateEnd",(String)request.getParameter("payDateEnd"));
        view.addObject("crruntPage",(String)request.getParameter("crruntPage"));
        //设置原始的查询数据

        view.setViewName("payDetailModify");
        return view;

    }
    //删除项目
    public ModelAndView deletePayDetail(HttpServletRequest request, long payId) throws UnsupportedEncodingException {
        ModelAndView view = new ModelAndView();
        dataBaseService.deletePayDetailByPrimaryKey(payId);
        view.addObject("itemName",(String)request.getParameter("itemName"));
        String subitemName=URLDecoder.decode(request.getParameter("subitemName"),"utf-8");

        view.addObject("subitemName",(String)request.getParameter("subitemName"));
        view.addObject("payPerson",(String)request.getParameter("payPerson"));
        view.addObject("payDateStart",(String)request.getParameter("payDateStart"));
        view.addObject("payDateEnd",(String)request.getParameter("payDateEnd"));
        view.addObject("crruntPage",(String)request.getParameter("crruntPage"));
        view.addObject("oldFlag","delete");
        //设置原始的查询数据

        view.setViewName("redirect:/payDetail/toPayDetailList");
        return view;

    }

    public ModelAndView personalInformation() {
        ModelAndView view = new ModelAndView();
        view.setViewName("personalInformation");
        return view;
    }

    public ModelAndView uploadPersonalPhoto(HttpServletRequest request) throws IOException {
        long  startTime=System.currentTimeMillis();
        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if(multipartResolver.isMultipart(request)){
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
            //获取multiRequest 中所有的文件名
            Iterator iter=multiRequest.getFileNames();
            while(iter.hasNext()){
                //一次遍历所有文件
                MultipartFile file=multiRequest.getFile(iter.next().toString());
                if(file!=null){
                    String path=personalPhotoPath+file.getOriginalFilename();
                    logger.info("path="+path);
                    //上传
                    file.transferTo(new File(path));
                }

            }
        }
        long  endTime=System.currentTimeMillis();
        System.out.println("方法三的运行时间："+String.valueOf(endTime-startTime)+"ms");
        //修改对应的图片url
        ModelAndView view = new ModelAndView();
        view.setViewName("personalInformation");
        return view;
    }
}


















