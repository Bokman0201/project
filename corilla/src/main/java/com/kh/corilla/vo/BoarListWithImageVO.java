package com.kh.corilla.vo;

import java.util.List;

import com.kh.corilla.dto.BoardDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class BoarListWithImageVO {
private List<BoardDto> boardDto;
private List<AttachVO> attachVO;
}
