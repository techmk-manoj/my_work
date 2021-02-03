package com.m3bi.common.customer;

import com.m3bi.common.jpa.converter.ZonedDateTimeConverter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.time.ZonedDateTime;

@Entity
@Table(name = "customer")
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String name;

    @Convert(converter = ZonedDateTimeConverter.class)
    @CreatedDate
    private ZonedDateTime createdAt;

    @Convert(converter = ZonedDateTimeConverter.class)
    @LastModifiedDate
    private ZonedDateTime updatedAt;
    
    private double points;

	public Customer orElseThrow(Object object) {
		// TODO Auto-generated method stub
		return null;
	}
    
}
