package com.epam.practice.practice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "weather_data")
@Getter
@Setter
@ToString
public class Weather extends BaseEntity {

    @Column(name = "city_name")
    private String city_name;

    @Column(name = "temp")
    private BigDecimal temp;

    @Column(name = "temp_feels")
    private BigDecimal temp_feels;

    @Column(name = "temp_min")
    private BigDecimal temp_min;

    @Column(name = "temp_max")
    private BigDecimal temp_max;
}