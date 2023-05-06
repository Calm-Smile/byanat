package com.example.ByanPrjt;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.lang.reflect.Array;
@JsonFormat(shape=JsonFormat.Shape.ARRAY)
@JsonIgnoreProperties(ignoreUnknown = true)
public record ArrayT (Tower ar)
{}

