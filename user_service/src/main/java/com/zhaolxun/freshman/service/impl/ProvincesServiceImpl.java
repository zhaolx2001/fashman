package com.zhaolxun.freshman.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhaolxun.freshman.entity.Major;
import com.zhaolxun.freshman.entity.Provinces;
import com.zhaolxun.freshman.entity.vo.ProvincesVo;
import com.zhaolxun.freshman.mapper.ProvincesMapper;
import com.zhaolxun.freshman.service.ProvincesService;
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
public class ProvincesServiceImpl extends ServiceImpl<ProvincesMapper, Provinces> implements ProvincesService {

    @Override
    public List<ProvincesVo> getNumber(Integer year) {
        QueryWrapper<Provinces> wrapper = new QueryWrapper<>();
        wrapper.eq("statistical_year", year);

        ArrayList<ProvincesVo> list = new ArrayList<>();
        List<Provinces> ProvincesList = baseMapper.selectList(wrapper);

        ProvincesVo provincesVo = null;
        for (Provinces Provinces : ProvincesList) {
            provincesVo = new ProvincesVo();
            provincesVo.setName(Provinces.getName());
            provincesVo.setValue(Provinces.getNumberReal());

            list.add(provincesVo);
        }
        return list;
    }

    @Override
    public List<Provinces> getAllName() {
        //得到全部校区并去重
        QueryWrapper<Provinces> wrapper = new QueryWrapper<>();
        wrapper.select("DISTINCT name");
        List<Provinces> list = baseMapper.selectList(wrapper);
        return list;
    }

    @Override
    public Map<String, Object> showChart(ProvincesVo provincesVo) {

        QueryWrapper<Provinces> wrapper = new QueryWrapper<>();
        wrapper.eq("name", provincesVo.getName());
        // TODO: 2022/4/30 柱形图表暂时不做，先做饼图
//        wrapper.between("statistical_year", provincesVo.getBegin(), provincesVo.getEnd());


        List<Provinces> collegesList = baseMapper.selectList(wrapper);

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
        for (Provinces colleges : collegesList) {

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
    public boolean isSameName(String provincesName, int yearInt) {

        QueryWrapper<Provinces> wrapper = new QueryWrapper<>();
        wrapper.eq("name", provincesName);
        wrapper.eq("statistical_year", yearInt);

        Integer count = baseMapper.selectCount(wrapper);

        return count > 0;
    }

    @Override
    public void addProvincesByInsStudent(String provincesName, Integer year) {
        QueryWrapper<Provinces> wrapper = new QueryWrapper<>();
        wrapper.eq("name", provincesName);
        wrapper.eq("statistical_year", year);

        Provinces isExist = baseMapper.selectOne(wrapper);

        Provinces provinces = null;
        //如果查不出数据就进行创建
        if (isExist == null) {
            provinces = new Provinces();
            provinces.setName(provincesName);
            provinces.setStatisticalYear(year);
            baseMapper.insert(provinces);
        }
    }

    @Override
    public List<Provinces> getProvince(String provincesName, Integer year) {
        QueryWrapper<Provinces> wrapper = new QueryWrapper<>();
        wrapper.eq("name", provincesName);
        wrapper.eq("statistical_year", year);
        List<Provinces> provinces = baseMapper.selectList(wrapper);
        return provinces;
    }
}
