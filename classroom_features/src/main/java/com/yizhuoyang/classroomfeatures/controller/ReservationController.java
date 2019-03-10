package com.yizhuoyang.classroomfeatures.controller;

import com.yizhuoyang.classroomfeatures.constant.Result;
import com.yizhuoyang.classroomfeatures.domain.ReservationInfo;
import com.yizhuoyang.classroomfeatures.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rsv")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }


    /**
     * 预约页面申请预约
     */
    @PostMapping(value = "/insertInfo")
    @ResponseBody
    public Result insertInfo(@RequestBody ReservationInfo reservationInfo) {
        return reservationService.insertInfo(reservationInfo);
    }


    /**
     * 学生预约界面
     */
    @GetMapping(value = "/getStudentRSVById")
    @ResponseBody
    public Result getStudentRSVById(@RequestParam("uid") Integer uid) {
        return reservationService.getStudentRSVById(uid);
    }

    /**
     * desc: 学生预约界面取消申请操作
     * <p>
     * request:
     * /rsv/cancelApplication?id=1
     * <p>
     * response:
     * {"code": 1,"message": "success","data": null}
     */
    @GetMapping(value = "/cancelApplication")
    @ResponseBody
    public Result cancelApplication(@RequestParam("id") Integer id) {
        return reservationService.cancelApplication(id);
    }

    /**
     * desc: 审批界面
     * <p>
     * request:
     * /rsv/getApprovalDetail?date=20190306
     * <p>
     * response:
     * {"code":1,"message":"success","data":"[{\"date\":20190306,\"id\":1,\"isPass\":0,\"reservationDesc\":\"计算机协会活动\",\"roomId\":0,\"roomNumber\":1,\"teachingBuilding\":\"教1\",\"time\":4,\"userId\":0,\"userName\":\"zhangsan\"},
     * {\"date\":20190306,\"id\":2,\"isPass\":0,\"reservationDesc\":\"欢乐时光\",\"roomId\":0,\"roomNumber\":1,\"teachingBuilding\":\"教1\",\"time\":4,\"userId\":0,\"userName\":\"lisi\"},
     * {\"date\":20190306,\"id\":3,\"isPass\":0,\"reservationDesc\":\"计算机协会活动\",\"roomId\":0,\"roomNumber\":2,\"teachingBuilding\":\"教1\",\"time\":4,\"userId\":0,\"userName\":\"zhangsan\"}]"}
     */
    @GetMapping(value = "/getApprovalDetail")
    @ResponseBody
    public Result getApprovalDetail(@RequestParam("date") Integer date) {
        return reservationService.getApprovalDetail(date);
    }

    /**
     * desc: 审批界面操作
     * <p>
     * request:
     * /rsv/approvalOperation?id=4&ope=4&desc=
     * /rsv/approvalOperation?id=4&ope=4&desc=驳回
     * <p>
     * response:
     * {"code": 1,"message": "success","data": null}
     */
    @GetMapping(value = "/approvalOperation")
    @ResponseBody
    public Result approvalOperation(@RequestParam("id") Integer id,@RequestParam("ope")  Integer ope, @RequestParam("desc") String desc) {
        return reservationService.approvalOperation(id, ope, desc);
    }


}
