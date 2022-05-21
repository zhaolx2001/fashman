package com.zhaolxun.freshman.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mysql.cj.QueryResult;
import com.zhaolxun.freshman.entity.Campus;
import com.zhaolxun.freshman.entity.vo.CampusVo;
import com.zhaolxun.freshman.mapper.CampusMapper;
import com.zhaolxun.freshman.service.CampusService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhaolxun.freshman.utils.DoubleToPercentFormat;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhaolxun
 * @since 2022-04-24
 */
@Service
public class CampusServiceImpl extends ServiceImpl<CampusMapper, Campus> implements CampusService {

    @Override
    public void deleteCampus(String id) {
        QueryWrapper<Campus> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);

        baseMapper.delete(wrapper);
    }

    @Override
    public Campus getPageCampus(Campus campus, Page<Campus> campusPage) {
        // TODO: 2022/4/24 带条件分页查询校区
        return null;
    }

    @Override
    public void addCampus(Campus campus) {
        baseMapper.insert(campus);
    }

    @Override
    public List<Campus> getAllCampusName() {
        //得到全部校区并去重
        QueryWrapper<Campus> wrapper = new QueryWrapper<>();
        wrapper.select("DISTINCT name");
        List<Campus> list = baseMapper.selectList(wrapper);
        return list;
    }

    @Override
    public Map<String, Object> showChart(CampusVo campusVo) {

        QueryWrapper<Campus> wrapper = new QueryWrapper<>();
        wrapper.eq("name", campusVo.getName());
        wrapper.between("statistical_year", campusVo.getBegin(), campusVo.getEnd());
        List<Campus> campusList = baseMapper.selectList(wrapper);

        Map<String, Object> map = new HashMap<>();

        //年份列表
        ArrayList<Integer> years = new ArrayList<>();
        //预期人数
        ArrayList<Integer> relMan = new ArrayList<>();
        //真实人数
        ArrayList<Integer> expMan = new ArrayList<>();
        //百分比
        ArrayList<Double> percentage = new ArrayList<>();

        map.put("years",years);
        map.put("relMan",relMan);
        map.put("expMan",expMan);
        map.put("percentage",percentage);

        //遍历添加值
        for (Campus campus : campusList) {
            Integer year = campus.getStatisticalYear();
            years.add(year);

            Integer numberReal = campus.getNumberReal();
            relMan.add(numberReal);

            Integer numberExpect = campus.getNumberExpect();
            expMan.add(numberExpect);

            double d1 = numberReal;
            double d2 = numberExpect;

            Double prop=(d1/d2) * 100;
            percentage.add(prop);
        }

        return map;
    }

    @Override
    public boolean isSameName(String campusName, int year) {

        QueryWrapper<Campus> wrapper = new QueryWrapper<>();
        wrapper.eq("name", campusName);
        wrapper.eq("statistical_year", year);

        Integer count = baseMapper.selectCount(wrapper);

        return count>0;
    }

    @Override
    public List<Campus> getCampusByYear(int year) {
        QueryWrapper<Campus> wrapper = new QueryWrapper<>();
        wrapper.eq("statistical_year", year);

        List<Campus> campusList = baseMapper.selectList(wrapper);
        return campusList;
    }


}
