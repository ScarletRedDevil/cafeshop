package com.sds.cafeshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sds.cafeshop.domain.SubCategory;
import com.sds.cafeshop.model.product.SubCategoryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SubCategoryController {
	
	@Autowired
	private SubCategoryService subCategoryService;
	
	@ResponseBody
    @GetMapping("/admin/subcategory/list")
    public List<SubCategory> getSubListByTopIdx(@RequestParam("topcategory_idx") int topcategory_idx, Model model) {
        List<SubCategory> subList = subCategoryService.selectAllByTopIdx(topcategory_idx);
        log.debug(topcategory_idx + "에 소속된 하위 카테고리는 " + subList);
        return subList;
    }
}



