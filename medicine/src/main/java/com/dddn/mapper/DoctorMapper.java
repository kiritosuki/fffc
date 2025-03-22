package com.dddn.mapper;

import com.dddn.pojo.Doctor;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DoctorMapper {

    Doctor getByUsernameAndPassword(Doctor doctor);

}
