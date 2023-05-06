package com.example.ByanPrjt;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//@JsonIgnoreProperties(ignoreUnknown = true)
@JsonFormat(shape=JsonFormat.Shape.ARRAY)
public record ArrayT (Tower ar)
{}

