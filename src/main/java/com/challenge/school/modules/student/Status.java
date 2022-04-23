package com.challenge.school.modules.student;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Status {
    APPROVED("approved"),
    DISAPPROVED("disapproved");

    final String value;
}
