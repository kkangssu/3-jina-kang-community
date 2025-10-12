package com.ktb.ktb_community.post.dto.response;

import com.ktb.ktb_community.global.file.dto.FileInfo;
import java.time.LocalDateTime;
import java.util.List;

public record PostDetailResponse (
    Long PostId,
    String title,
    String authorName,
    String content,
    Long likeCount,
    Long viewCount,
    LocalDateTime createdAt,

    // 사진
    List<FileInfo> postImages
){}
