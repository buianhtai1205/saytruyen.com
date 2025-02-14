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
import vn.com.saytruyen.user_service.request.ItemRequest;
import vn.com.saytruyen.user_service.response.ItemResponse;

/**
 * The interface Item controller.
 */
@RestController
@RequestMapping("/api/user-service/item")
public interface ItemController {

    /**
     * Gets list item.
     *
     * @param pageNumber the page number
     * @param pageSize   the page size
     * @return the list item
     */
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<PageableResponse> getListItem(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                              @RequestParam(value = "pageSize", required = false) Integer pageSize);

    /**
     * Create item api response.
     *
     * @param itemRequest the item request
     * @return the api response
     */
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    ApiResponse<Boolean> createItem(@RequestBody ItemRequest itemRequest);

    /**
     * Update item api response.
     *
     * @param itemRequest the item request
     * @param id          the id
     * @return the api response
     */
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<Boolean> updateItem(@RequestBody ItemRequest itemRequest,
                                    @PathVariable Long id);

    /**
     * Delete item api response.
     *
     * @param id the id
     * @return the api response
     */
    @PutMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<Boolean> deleteItem(@PathVariable Long id);

    /**
     * Gets item.
     *
     * @param id the id
     * @return the item
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<ItemResponse> getItem(@PathVariable Long id);
}