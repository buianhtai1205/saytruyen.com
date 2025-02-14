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
import vn.com.saytruyen.user_service.request.EquipLocationRequest;
import vn.com.saytruyen.user_service.response.EquipLocationResponse;

/**
 * The interface Equip location controller.
 */
@RestController
@RequestMapping("/api/user-service/equipLocation")
public interface EquipLocationController {

    /**
     * Gets list equip location.
     *
     * @param pageNumber the page number
     * @param pageSize   the page size
     * @return the list equip location
     */
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<PageableResponse> getListEquipLocation(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                       @RequestParam(value = "pageSize", required = false) Integer pageSize);

    /**
     * Create equip location api response.
     *
     * @param equipLocationRequest the equip location request
     * @return the api response
     */
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    ApiResponse<Boolean> createEquipLocation(@RequestBody EquipLocationRequest equipLocationRequest);

    /**
     * Update equip location api response.
     *
     * @param equipLocationRequest the equip location request
     * @param id                   the id
     * @return the api response
     */
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<Boolean> updateEquipLocation(@RequestBody EquipLocationRequest equipLocationRequest,
                                             @PathVariable Long id);

    /**
     * Delete equip location api response.
     *
     * @param id the id
     * @return the api response
     */
    @PutMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<Boolean> deleteEquipLocation(@PathVariable Long id);

    /**
     * Gets equip location.
     *
     * @param id the id
     * @return the equip location
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<EquipLocationResponse> getEquipLocation(@PathVariable Long id);
}