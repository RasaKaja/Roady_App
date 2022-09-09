package com.roady.app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewInfo {

    private List<ReviewPartAndUser> users = new ArrayList<>();

}