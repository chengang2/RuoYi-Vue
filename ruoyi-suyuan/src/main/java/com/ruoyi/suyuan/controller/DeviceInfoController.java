package com.ruoyi.suyuan.controller;

import java.util.*;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.suyuan.domain.*;
import com.ruoyi.suyuan.service.IDeviceMonitorService;
import com.ruoyi.suyuan.service.IDeviceVoiceService;
import com.ruoyi.suyuan.service.IEnterpriseService;
import com.ruoyi.suyuan.tools.DateFormatUtil;
import com.ruoyi.suyuan.tools.ShipingTokenUtil;
import io.jsonwebtoken.lang.Strings;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.suyuan.service.IDeviceInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 设备管理Controller
 * 
 * @author ruoyi
 * @date 2025-05-12
 */
@Api(tags = "溯源-设备管理")
@RestController
@RequestMapping("/suyuan/device")
public class DeviceInfoController extends BaseController
{
    @Autowired
    private ShipingTokenUtil shipingTokenUtil;
    @Autowired
    private IDeviceInfoService deviceInfoService;
    @Autowired
    private IDeviceVoiceService deviceVoiceService;
    @Autowired
    private IDeviceMonitorService deviceMonitorService;
    @Autowired
    private IEnterpriseService enterpriseService;

    private final ObjectMapper objectMapper = new ObjectMapper();
    /**
     * 查询设备管理列表
     */
    @PreAuthorize("@ss.hasPermi('suyuan:device_info:list')")
    @ApiOperation("获取设备列表,原接口: /device/getAllByPage")
    @GetMapping("/list")
    public TableDataInfo list(DeviceInfoVO deviceInfoVO)
    {

        List<DeviceInfo> list = deviceInfoService.selectDeviceInfoList(deviceInfoVO);
        startPage();
        List<DeviceListVO> voList = new ArrayList<>();
        for (DeviceInfo device : list) {
            DeviceListVO vo = new DeviceListVO();
            // 1. 获取企业信息
            Integer enterpriseId = device.getEnterpriseId();
            Enterprise enterprise = enterpriseService.selectEnterpriseById(enterpriseId);
            vo.setId(device.getId());
            vo.setEnterpriseId(enterpriseId);
            vo.setEnterpriseName(enterprise != null ? enterprise.getName() : "");
            vo.setDeviceAddress(device.getDeviceAddress());
            vo.setMonitorDeviceIds(device.getMonitorDeviceIds());
            vo.setVoiceDeviceIds(device.getVoiceDeviceIds());
            vo.setStatus(device.getStatus());
            vo.setLatLon(device.getLatLon());
            // 格式化时间
            vo.setCreateTime(DateFormatUtil.format(device.getCreateTime()));
            vo.setUpdateTime(DateFormatUtil.format(device.getUpdateTime()));
            voList.add(vo);

        }
        return getDataTable(voList);
    }

    /**
     * 获取设备管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('suyuan:device_info:list')")
    @ApiOperation("获取设备列表,原接口: /device/getAllVoice")
    @GetMapping(value = "/getAllVoice")
    public AjaxResult getAllVoice(DeviceInfoVO deviceInfoVO){
        Map<String, Object> data = new HashMap<>();

        List<DeviceInfo> list = deviceInfoService.selectDeviceInfoList(deviceInfoVO);
        List<String> voiceIds = new ArrayList<>();
        for (DeviceInfo device : list) {
            String voiceDeviceIds = device.getVoiceDeviceIds();
            if(voiceDeviceIds != null && !voiceDeviceIds.isEmpty()){
                voiceIds.addAll(Arrays.asList(voiceDeviceIds.split(",")));
            }
        }
        DeviceVoiceVO deviceVoice = new DeviceVoiceVO();
        deviceVoice.setVoiceDeviceSerials(voiceIds);
        List<DeviceVoice> deviceVoices = deviceVoiceService.selectDeviceVoiceList(deviceVoice);
        String tokenResult = shipingTokenUtil.getShipingToken();
        JsonNode tokenJson = null;
        try {
            tokenJson = objectMapper.readTree(tokenResult);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        List<Map<String, Object>> items = new ArrayList<>();
        String accessToken = tokenJson.path("data").path("accessToken").asText();
        for(DeviceVoice v : deviceVoices){
            Map<String, Object> item = new HashMap<>();
            item.put("id",v.getId());
            item.put("voice_device_serial",v.getVoiceDeviceSerial());
            item.put("voice_url",v.getVoiceUrl());
            item.put("voice_token",accessToken);
            item.put("createTime",DateFormatUtil.format(v.getCreateTime()));
            item.put("updateTime", DateFormatUtil.format(v.getUpdateTime()));

            items.add(item);
        }
        data.put("items",items);

        return success(data);
    }

    /**
     * 获取设备管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('suyuan:device_info:list')")
    @ApiOperation("获取设备列表,原接口: /device/getAllMonitor")
    @GetMapping(value = "/getAllMonitor")
    public AjaxResult getAllMonitor(DeviceInfoVO deviceInfoVO){
        Map<String, Object> data = new HashMap<>();

        List<DeviceInfo> list = deviceInfoService.selectDeviceInfoList(deviceInfoVO);
        List<String> monitorIds = new ArrayList<>();
        for (DeviceInfo device : list) {
            String monitorDeviceIds = device.getMonitorDeviceIds();
            if(monitorDeviceIds != null && !monitorDeviceIds.isEmpty()){
                monitorIds.addAll(Arrays.asList(monitorDeviceIds.split(",")));
            }
        }

        List<Map<String, Object>> items = new ArrayList<>();

        for(String deviceId : monitorIds){
            Map<String, Object> item = new HashMap<>();

            DeviceMonitor deviceMonitor = deviceMonitorService.selectDeviceMonitorByDeviceId(deviceId);
            if (deviceMonitor != null){
                item.put("id",deviceMonitor.getId());
                item.put("device_id",deviceMonitor.getDeviceId());
                item.put("datas",deviceMonitor.getDatas());
                item.put("date",deviceMonitor.getDate());
                item.put("createTime",DateFormatUtil.format(deviceMonitor.getCreateTime()));
                item.put("updateTime", DateFormatUtil.format(deviceMonitor.getUpdateTime()));

                items.add(item);
            }

        }
        data.put("items",items);

        return success(data);
    }


    ///**
    // * 导出设备管理列表
    // */
    //@PreAuthorize("@ss.hasPermi('suyuan:device_info:export')")
    //@Log(title = "设备管理", businessType = BusinessType.EXPORT)
    //@PostMapping("/export")
    //public void export(HttpServletResponse response, DeviceInfo deviceInfo)
    //{
    //    List<DeviceInfo> list = deviceInfoService.selectDeviceInfoList(deviceInfo);
    //    ExcelUtil<DeviceInfo> util = new ExcelUtil<DeviceInfo>(DeviceInfo.class);
    //    util.exportExcel(response, list, "设备管理数据");
    //}

    /**
     * 获取设备管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('suyuan:device_info:query')")
    @ApiOperation("获取设备详情,原接口: /device/get")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        Map<String, Object> data = new HashMap<>();

        // 1. 获取设备信息
        DeviceInfo deviceInfo = deviceInfoService.selectDeviceInfoById(id);
        if (deviceInfo == null) {
            return AjaxResult.error(1,"设备信息不存在");
        }
        // 2. 获取企业信息
        Integer enterpriseId = deviceInfo.getEnterpriseId();
        Enterprise enterprise = enterpriseService.selectEnterpriseById(enterpriseId);
        String enterpriseName = enterprise.getName();

        // 3. 语音设备信息
        List<Map<String, Object>> voiceItems = new ArrayList<>();
        String voiceDeviceIds = deviceInfo.getVoiceDeviceIds();
        if (voiceDeviceIds != null && !voiceDeviceIds.isEmpty()) {
            for (String voiceDeviceId : voiceDeviceIds.split(",")) {
                DeviceVoiceVO deviceVoice = new DeviceVoiceVO();
                deviceVoice.setVoiceDeviceSerial(voiceDeviceId);
                List<DeviceVoice> deviceVoices = deviceVoiceService.selectDeviceVoiceList(deviceVoice);

                for (DeviceVoice v : deviceVoices) {
                    String tokenResult = shipingTokenUtil.getShipingToken();
                    JsonNode tokenJson = null;
                    try {
                        tokenJson = objectMapper.readTree(tokenResult);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                    String voiceToken = tokenJson.path("data").path("accessToken").asText();
                    Map<String, Object> voiceItem = new HashMap<>();
                    voiceItem.put("voice_device_serial", v.getVoiceDeviceSerial());
                    voiceItem.put("voice_url", v.getVoiceUrl());
                    voiceItem.put("voice_token", voiceToken);
                    voiceItems.add(voiceItem);
                }
            }
        }
        // 4. 监控设备信息
        List<Map<String, Object>> monitorItems = new ArrayList<>();
        String monitorDeviceIds = deviceInfo.getMonitorDeviceIds();
        if (monitorDeviceIds != null && !monitorDeviceIds.isEmpty()) {
            for (String monitorId : monitorDeviceIds.split(",")) {
                DeviceMonitor monitor = deviceMonitorService.selectDeviceMonitorByDeviceId(monitorId);
                if (monitor != null) {
                    Map<String, Object> monitorItem = new HashMap<>();
                    monitorItem.put("device_id", monitor.getDeviceId());
                    monitorItem.put("datas", monitor.getDatas());
                    monitorItem.put("date", monitor.getDate());
                    monitorItems.add(monitorItem);
                }
            }
        }
        // 5. 组装返回数据
        data.put("id", deviceInfo.getId());
        data.put("device_address", deviceInfo.getDeviceAddress());
        data.put("status", deviceInfo.getStatus());
        data.put("enterprise_name", enterpriseName);
        data.put("voice_items", voiceItems);
        data.put("monitor_items", monitorItems);
        data.put("lat_lon", deviceInfo.getLatLon());
        data.put("createtime", DateFormatUtil.format(deviceInfo.getCreateTime()));
        data.put("updatetime", DateFormatUtil.format(deviceInfo.getUpdateTime()));

        return success(data);
    }

    /**
     * 新增设备管理
     */
    @PreAuthorize("@ss.hasPermi('suyuan:device_info:add')")
    @Log(title = "设备管理", businessType = BusinessType.INSERT)
    @ApiOperation("新增设备,原接口: /device/create")
    @PostMapping
    public AjaxResult add(@RequestBody DeviceInfo deviceInfo)
    {
        try{
            String voiceDeviceIds = deviceInfo.getVoiceDeviceIds();

            if (voiceDeviceIds != null && !voiceDeviceIds.isEmpty()) {
                for (String voiceDeviceId : voiceDeviceIds.split(",")){
                    System.out.println("voiceDeviceId = " + voiceDeviceId);
                    // 1. 获取token
                    String tokenResult = shipingTokenUtil.getShipingToken();
                    JsonNode tokenJson = objectMapper.readTree(tokenResult);
                    String accessToken = tokenJson.path("data").path("accessToken").asText();

                    // 2. 获取播放url
                    String ezopenResult = shipingTokenUtil.getEzopen(accessToken, voiceDeviceId);
                    JsonNode ezopenJson = objectMapper.readTree(ezopenResult);
                    String codeStr = ezopenJson.path("code").asText();
                    if ("200".equals(codeStr)) {
                        String voiceUrl = ezopenJson.path("data").path("url").asText();
                        DeviceVoice voice = new DeviceVoice();
                        voice.setVoiceDeviceSerial(voiceDeviceId);
                        voice.setVoiceUrl(voiceUrl);
                        deviceVoiceService.insertDeviceVoice(voice); // 插入数据库
                    } else {
                        return AjaxResult.error(1,"获取设备号:" + voiceDeviceId + "的视频播放地址失败,请联系管理员");
                    }
                }
            }

            return toAjax(deviceInfoService.insertDeviceInfo(deviceInfo));
        }catch (Exception e) {
            return AjaxResult.error(1,e.getMessage());
        }

    }

    /**
     * 修改设备管理
     */
    @PreAuthorize("@ss.hasPermi('suyuan:device_info:edit')")
    @Log(title = "设备管理", businessType = BusinessType.UPDATE)
    @ApiOperation("修改设备,原接口: /device/update")
    @PutMapping
    public AjaxResult edit(@RequestBody DeviceInfo deviceInfo)
    {
        return toAjax(deviceInfoService.updateDeviceInfo(deviceInfo));
    }

    /**
     * 删除设备管理
     */
    @PreAuthorize("@ss.hasPermi('suyuan:device_info:remove')")
    @Log(title = "设备管理", businessType = BusinessType.DELETE)
    @ApiOperation("删除设备,多个id之间用逗号隔开。原接口: /device/delete")
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(deviceInfoService.deleteDeviceInfoByIds(ids));
    }

    @ApiOperation("前端新增设备。原接口: /device/monitor_info")
    @PostMapping("/monitor_info")
    public AjaxResult addMonitorInfo(@RequestBody MonitorInfo monitorInfo){

        DeviceMonitor deviceMonitor = new DeviceMonitor();
        deviceMonitor.setDeviceId(monitorInfo.getDeviceId());
        deviceMonitor.setDatas(monitorInfo.getDatas());
        deviceMonitor.setDate(monitorInfo.getDate());

        return toAjax(deviceMonitorService.insertDeviceMonitor(deviceMonitor));

    }

    @ApiOperation("前端获取设备详情。原接口: /device/get_detail")
    @GetMapping("/get_detail/{enterpriseId}")
    public AjaxResult getDetall(@PathVariable("enterpriseId") Integer enterpriseId){
        Map<String, Object> data = new HashMap<>();

        DeviceInfoVO deviceInfo = new DeviceInfoVO();
        deviceInfo.setEnterpriseId(enterpriseId);
        List<DeviceInfo> deviceInfos = deviceInfoService.selectDeviceInfoList(deviceInfo);
        List<Map<String, Object>> items = new ArrayList<>();
        for(DeviceInfo device : deviceInfos){
            Map<String, Object> item = new HashMap<>();
            Integer eid = device.getEnterpriseId();

            Enterprise enterprise = enterpriseService.selectEnterpriseById(eid);
            String enterpriseName = enterprise.getName();

            // 3. 语音设备信息
            List<Map<String, Object>> voiceItems = new ArrayList<>();
            String voiceDeviceIds = device.getVoiceDeviceIds();
            System.out.println("voiceDeviceIds = " + voiceDeviceIds);
            if (voiceDeviceIds != null && !voiceDeviceIds.isEmpty()) {
                for (String voiceDeviceId : voiceDeviceIds.split(",")) {
                    DeviceVoiceVO deviceVoice = new DeviceVoiceVO();
                    deviceVoice.setVoiceDeviceSerial(voiceDeviceId);
                    List<DeviceVoice> deviceVoices = deviceVoiceService.selectDeviceVoiceList(deviceVoice);
                    System.out.println("deviceVoices.size() = " + deviceVoices.size());
                    for (DeviceVoice v : deviceVoices) {
                        String tokenResult = shipingTokenUtil.getShipingToken();
                        JsonNode tokenJson = null;
                        try {
                            tokenJson = objectMapper.readTree(tokenResult);
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }
                        String voiceToken = tokenJson.path("data").path("accessToken").asText();
                        Map<String, Object> voiceItem = new HashMap<>();
                        voiceItem.put("voice_device_serial", v.getVoiceDeviceSerial());
                        voiceItem.put("voice_url", v.getVoiceUrl());
                        voiceItem.put("voice_token", voiceToken);
                        voiceItems.add(voiceItem);
                    }
                }
            }
            // 4. 监控设备信息
            List<Map<String, Object>> monitorItems = new ArrayList<>();
            String monitorDeviceIds = device.getMonitorDeviceIds();
            if (monitorDeviceIds != null && !monitorDeviceIds.isEmpty()) {
                for (String monitorId : monitorDeviceIds.split(",")) {
                    DeviceMonitor monitor = deviceMonitorService.selectDeviceMonitorByDeviceId(monitorId);
                    if (monitor != null) {
                        Map<String, Object> monitorItem = new HashMap<>();
                        monitorItem.put("device_id", monitor.getDeviceId());
                        monitorItem.put("datas", monitor.getDatas());
                        monitorItem.put("date", monitor.getDate());
                        monitorItems.add(monitorItem);
                    }
                }
            }
            // 5. 组装返回数据
            item.put("id", device.getId());
            item.put("device_address", device.getDeviceAddress());
            item.put("status", device.getStatus());
            item.put("enterprise_name", enterpriseName);
            item.put("voice_items", voiceItems);
            item.put("monitor_items", monitorItems);
            item.put("lat_lon", device.getLatLon());
            item.put("createtime", DateFormatUtil.format(device.getCreateTime()));
            item.put("updatetime", DateFormatUtil.format(device.getUpdateTime()));

            items.add(item);
        }

        data.put("items",items);

        return success(data);
    }

}
