package com.project.catalog.record;

import java.util.List;

public record PagedResult<T>(

        List<T> data,
        long totalElement,
        int pageNumber,
        int totalPages,
        boolean isFirst,
        boolean isLast,
        boolean hasNext,
        boolean hasPrevious

) { }
