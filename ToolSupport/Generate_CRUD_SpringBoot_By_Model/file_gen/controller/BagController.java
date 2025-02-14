package vn.com.saytruyen.user_service.controller;

import io.github.buianhtai1205.saytruyen_common_service.response.ApiResponse;
import io.github.buianhtai1205.saytruyen_common_service.response.PageableResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import vn.com.saytruyen.user_service.request.BagRequest;
import vn.com.saytruyen.user_service.response.BagResponse;

@RestController
@RequestMapping("/api/bag")
public interface BagController {

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<PageableResponse> getListBag(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                          @RequestParam(value = "pageSize", required = false) Integer pageSize);

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    ApiResponse<Boolean> createBag(@RequestBody BagRequest bagRequest);

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<Boolean> updateBag(@RequestBody BagRequest bagRequest,
                                                @PathVariable Long id);

    @PutMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<Boolean> deleteBag(@PathVariable Long id);

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<BagResponse> getBag(@PathVariable Long id);
}