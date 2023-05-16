package com.mock.CarParkingManagement.model.others;


import lombok.*;

import java.util.List;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CustomPage<T> {
    private List<T> content;
    private Object currentPage;
    private Object totalItems;
    private Object totalPages;
}
