package com.blas.blascommon.utils.fileutils;

import static com.blas.blascommon.constants.HttpResponseConfiguration.ACCEPT_RANGES;
import static com.blas.blascommon.constants.HttpResponseConfiguration.BYTES;
import static com.blas.blascommon.constants.HttpResponseConfiguration.CHUNK_SIZE;
import static com.blas.blascommon.constants.HttpResponseConfiguration.CONTENT_LENGTH;
import static com.blas.blascommon.constants.HttpResponseConfiguration.CONTENT_RANGE;
import static com.blas.blascommon.constants.HttpResponseConfiguration.CONTENT_TYPE;
import static com.blas.blascommon.constants.HttpResponseConfiguration.VIDEO_CONTENT;
import static com.blas.blascommon.utils.fileutils.FileUtils.getFileSize;
import static com.blas.blascommon.utils.fileutils.FileUtils.readBytesRange;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtils {

  private ResponseUtils() {
  }

  public static ResponseEntity<byte[]> prepareResponseVideo(final String path,
      final String fileType, final String range) {
    long rangeStart = 0;
    long rangeEnd = CHUNK_SIZE;
    final long fileSize = getFileSize(path);
    if (range == null) {
      return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
          .header(CONTENT_TYPE, VIDEO_CONTENT + fileType).header(ACCEPT_RANGES, BYTES)
          .header(CONTENT_LENGTH, String.valueOf(rangeEnd))
          .header(CONTENT_RANGE, BYTES + " " + rangeStart + "-" + rangeEnd + "/" + fileSize)
          .header(CONTENT_LENGTH, String.valueOf(fileSize))
          .body(readBytesRange(path, rangeStart, rangeEnd));
    }
    String[] ranges = range.split("-");
    rangeStart = Long.parseLong(ranges[0].substring(6));
    if (ranges.length > 1) {
      rangeEnd = Long.parseLong(ranges[1]);
    } else {
      rangeEnd = rangeStart + CHUNK_SIZE;
    }
    rangeEnd = Math.min(rangeEnd, fileSize - 1);
    final byte[] data = readBytesRange(path, rangeStart, rangeEnd);
    final String contentLength = String.valueOf((rangeEnd - rangeStart) + 1);
    HttpStatus httpStatus = HttpStatus.PARTIAL_CONTENT;
    if (rangeEnd >= fileSize) {
      httpStatus = HttpStatus.OK;
    }
    return ResponseEntity.status(httpStatus).header(CONTENT_TYPE, VIDEO_CONTENT + fileType)
        .header(ACCEPT_RANGES, BYTES).header(CONTENT_LENGTH, contentLength)
        .header(CONTENT_RANGE, BYTES + " " + rangeStart + "-" + rangeEnd + "/" + fileSize)
        .body(data);
  }
}