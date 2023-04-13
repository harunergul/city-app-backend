package com.aaron.iluslinn.dto;

import java.util.List;

import lombok.Builder;

@Builder
public record CityListRsp(PageVo pageInfo, List<CityDTO> data) {
}
