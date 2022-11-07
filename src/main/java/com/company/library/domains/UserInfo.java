package com.company.library.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private Integer number;
    private Integer totalPages;
    private Boolean hasPrevious;
    private Boolean hasNext;
}
