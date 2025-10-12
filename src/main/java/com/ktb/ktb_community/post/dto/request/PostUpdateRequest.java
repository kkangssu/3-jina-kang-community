package com.ktb.ktb_community.post.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public class PostUpdateRequest {

    @Size(max = 255, message = "제목은 최대 255자입니다")
    String title;

    String content;

    List<String> fileUrls;
}
