package com.joysun.controller;

import com.joysun.service.ItemInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author joysun
 * @create 2021-10-22 16:52
 */
@RestController
@RequestMapping("/itemInfo")
@CrossOrigin
public class ItemInfoController {
    @Autowired
    private ItemInfoService itemInfoService;
    /**
     * 库存递减
     * @param id
     * @param count
     * @return
     */
    @PostMapping(value = "/decrCount")
    public String decrCount(@RequestParam(value = "id") int id, @RequestParam(value = "count") int count){
        //库存递减
        itemInfoService.decrCount(id,count);
        return "success";
    }
}
