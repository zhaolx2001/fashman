package com.zhaolxun.freshman.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhaolxun.freshman.entity.Campus;
import com.zhaolxun.freshman.entity.Class;
import com.zhaolxun.freshman.entity.Colleges;
import com.zhaolxun.freshman.mapper.ClassMapper;
import com.zhaolxun.freshman.service.ClassService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhaolxun
 * @since 2022-04-24
 */
@Service
public class ClassServiceImpl extends ServiceImpl<ClassMapper, Class> implements ClassService {

    @Override
    public List<Integer> getNumber(String campusId, String collegeId) {
        QueryWrapper<Class> wrapper = new QueryWrapper<>();
        wrapper.eq("campus_id", campusId);
        wrapper.eq("colleges_id", collegeId);

        Integer sumNumberExpect = 0;
        Integer sumNumberReal = 0;
        ArrayList<Integer> list = new ArrayList<>();
        list.add(sumNumberExpect);
        list.add(sumNumberReal);

        List<Class> collegesList = baseMapper.selectList(wrapper);
        for (Class classes : collegesList) {
            Integer numberExpect = classes.getNumberExpect();
            Integer numberReal = classes.getNumberReal();

            sumNumberExpect+=numberExpect;
            sumNumberReal+=numberReal;
        }
        return list;
    }

    @Override
    public List<Class> getAllName() {
        //得到全部校区并去重
        QueryWrapper<Class> wrapper = new QueryWrapper<>();
        wrapper.select("DISTINCT name");
        List<Class> list = baseMapper.selectList(wrapper);
        return list;
    }

    @Override
    public List<Class> getClassByCampusId(String collegeId) {
        QueryWrapper<Class> wrapper = new QueryWrapper<>();
        wrapper.eq("colleges_id", collegeId);

        List<Class> classes = baseMapper.selectList(wrapper);
        return classes;
    }

    @Override
    public boolean isSameName(String name, Integer year) {
        QueryWrapper<Class> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        wrapper.eq("statistical_year", year);

        Integer count = baseMapper.selectCount(wrapper);

        return count>0;
    }
}
