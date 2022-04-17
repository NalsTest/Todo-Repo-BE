package com.nals_test.todo.controllers;

import com.nals_test.todo.model.dto.WorkDTO;
import com.nals_test.todo.model.entity.Work;
import com.nals_test.todo.services.WorkService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin()
@RequestMapping(value = "api/todo")
public class WorkController {
    @Autowired
    private WorkService workService;

    @PostMapping()
    public ResponseEntity<Work> createWork(@Valid @RequestBody WorkDTO workDTO, BindingResult bindingResult) {
        new WorkDTO().validate(workDTO, bindingResult);
        Work work = new Work();
        if (!bindingResult.hasErrors()) {
            BeanUtils.copyProperties(workDTO, work);
            work.setStatus(0);
            work.setFlagDelete(false);
            this.workService.add(work);
            return new ResponseEntity<>(work, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * @return todo
     * @author LinhDN
     * @description phương thức lấy ra 1 đối tượng work bằng id
     * @since 16/04/2022 19:03
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Work> getWorkById(@PathVariable(value = "id") Integer id) {
        Work work = this.workService.findById(id);
        if (work != null) {
            return new ResponseEntity<>(work, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * @param id
     * @param workDTO
     * @param bindingResult
     * @return trạng thái thêm mới thành công hay không
     * @description chỉnh sửa thông tin của work theo id
     * @since 16/04/2022 19:03
     */
    @PatchMapping(value = "/{id}/edit")
    public ResponseEntity<Work> editWork(@PathVariable(value = "id") Integer id, @Valid @RequestBody WorkDTO workDTO, BindingResult bindingResult) {
        new WorkDTO().validate(workDTO, bindingResult);
        Work work = null;
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if ((work = this.workService.findById(id)) != null) {
            BeanUtils.copyProperties(workDTO, work);
            work.setId(id);
            this.workService.add(work);
            return new ResponseEntity<>(work, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * @param id
     * @return trạng thái xoá thành công hay không
     * @description xoá work theo id, bằng cách tạo trường boolean khi chuyển về true có nghĩa là xoá
     * @since 17/04/2022 03:45
     */
    @PatchMapping(value = "/{id}/delete")
    public ResponseEntity<Work> deleteWork(@PathVariable("id") Integer id) {
        Work work = null;
        if ((work = this.workService.findById(id)) != null) {
            this.workService.delete(id);
            return new ResponseEntity<>(work, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * @param pageable
     * @return page todo
     * @description Tìm kiếm work tổng hợp nhiều điều kiện:  gần đúng theo workname, status, starting date, ending date
     * @since 17/04/2022 14:45
     */
    @GetMapping()
    public ResponseEntity<Page<Work>> findWork(@RequestParam(name = "workName", required = false) String workName,
                                               @RequestParam(name = "status", required = false) Integer status,
                                               @RequestParam(name = "startingDate", required = false) String startingDate,
                                               @RequestParam(name = "endingDate", required = false) String endingDate,
                                               Pageable pageable) {
        Page<Work> todos = this.workService.searchWork(workName, status, startingDate, endingDate, pageable);
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    /**
     * @param pageable
     * @return page todo
     * @description Tìm kiếm work theo khoảng thời gian starting date
     * @since 17/04/2022 15:45
     */
    @GetMapping("/startingDate")
    public ResponseEntity<Page<Work>> findWorkByStartingDate(@RequestParam(name = "from", required = false) String from,
                                                             @RequestParam(name = "to", required = false) String to,
                                                             Pageable pageable) {
        Page<Work> todos = this.workService.searchWorkByStartingDate(from, to, pageable);
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    /**
     * @param pageable
     * @return page todo
     * @description Tìm kiếm work theo khoảng thời gian ending date
     * @since 17/04/2022 15:45
     */
    @GetMapping("/endingDate")
    public ResponseEntity<Page<Work>> findWorkByEndingDate(@RequestParam(name = "from", required = false) String from,
                                                           @RequestParam(name = "to", required = false) String to,
                                                           Pageable pageable) {
        Page<Work> todos = this.workService.searchWorkByEndingDate(from, to, pageable);
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }
}