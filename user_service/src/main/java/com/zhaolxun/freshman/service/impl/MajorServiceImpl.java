package com.zhaolxun.freshman.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhaolxun.freshman.entity.Class;
import com.zhaolxun.freshman.entity.Colleges;
import com.zhaolxun.freshman.entity.Major;
import com.zhaolxun.freshman.entity.vo.MajorVo;
import com.zhaolxun.freshman.mapper.MajorMapper;
import com.zhaolxun.freshman.service.MajorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhaolxun
 * @since 2022-04-24
 */
@Service
public class MajorServiceImpl extends ServiceImpl<MajorMapper, Major> implements MajorService {

    @Override
    public List<Integer> getNumber() {

        Integer sumNumberExpect = 0;
        Integer sumNumberReal = 0;
        ArrayList<Integer> list = new ArrayList<>();
        list.add(sumNumberExpect);
        list.add(sumNumberReal);

        List<Major> majorList = baseMapper.selectList(null);
        for (Major major : majorList) {
            Integer expect = major.getNumberExpect();
            Integer real = major.getNumberReal();

            sumNumberExpect += expect;
            sumNumberReal += real;
        }
        return list;
    }

    @Override
    public List<Major> getAllName() {
        //得到全部校区并去重
        QueryWrapper<Major> wrapper = new QueryWrapper<>();
        wrapper.select("DISTINCT name");
        List<Major> list = baseMapper.selectList(wrapper);
        return list;
    }

    @Override
    public Map<String, Object> showChart(MajorVo majorVo) {
        QueryWrapper<Major> wrapper = new QueryWrapper<>();
        wrapper.eq("name", majorVo.getName());
        wrapper.between("statistical_year", majorVo.getBegin(), majorVo.getEnd());

        List<Major> collegesList = baseMapper.selectList(wrapper);

        Map<String, Object> map = new HashMap<>();

        //年份列表
        ArrayList<Integer> years = new ArrayList<>();
        //预期人数
        ArrayList<Integer> relMan = new ArrayList<>();
        //真实人数
        ArrayList<Integer> expMan = new ArrayList<>();
        //百分比
        ArrayList<Double> percentage = new ArrayList<>();

        map.put("years", years);
        map.put("relMan", relMan);
        map.put("expMan", expMan);
        map.put("percentage", percentage);

        //遍历添加值
        for (Major colleges : collegesList) {

            Integer year = colleges.getStatisticalYear();
            years.add(year);

            Integer numberReal = colleges.getNumberReal();
            relMan.add(numberReal);

            Integer numberExpect = colleges.getNumberExpect();
            expMan.add(numberExpect);

            double d1 = numberReal;
            double d2 = numberExpect;

            Double prop = (d1 / d2) * 100;
            percentage.add(prop);
        }

        return map;
    }

    @Override
    public boolean isSameName(String majorName, int yearInt) {

        QueryWrapper<Major> wrapper = new QueryWrapper<>();
        wrapper.eq("name", majorName);
        wrapper.eq("statistical_year", yearInt);

        Integer count = baseMapper.selectCount(wrapper);

        return count > 0;
    }

    @Override
    public List<Major> getMajorByClassId(String classId) {
        QueryWrapper<Major> wrapper = new QueryWrapper<>();
        wrapper.eq("class_id", classId);

        List<Major> majorList = baseMapper.selectList(wrapper);
        return majorList;
    }

    @Override
    public List<Major> getMajor(String name, Integer begin, Integer end) {
        QueryWrapper<Major> majorQueryWrapper = new QueryWrapper<>();
        majorQueryWrapper.eq("name", name);
        majorQueryWrapper.between("statistical_year", begin, end);

        List<Major> majorList = baseMapper.selectList(majorQueryWrapper);
        return majorList;
    }
}
