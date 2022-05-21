package com.zhaolxun.freshman.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mysql.cj.QueryResult;
import com.zhaolxun.freshman.entity.Campus;
import com.zhaolxun.freshman.entity.Colleges;
import com.zhaolxun.freshman.entity.vo.CollegesVo;
import com.zhaolxun.freshman.mapper.CollegesMapper;
import com.zhaolxun.freshman.service.CampusService;
import com.zhaolxun.freshman.service.ClassService;
import com.zhaolxun.freshman.service.CollegesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhaolxun
 * @since 2022-04-24
 */
@Service
public class CollegesServiceImpl extends ServiceImpl<CollegesMapper, Colleges> implements CollegesService {


    @Autowired
    private CampusService campusService;

    @Autowired
    private CollegesService collegesService;

    @Override
    public List<Integer> getNumber(String campusId) {
        QueryWrapper<Colleges> wrapper = new QueryWrapper<>();
        wrapper.eq("campus_id", campusId);

        Integer sumNumberExpect = 0;
        Integer sumNumberReal = 0;
        ArrayList<Integer> list = new ArrayList<>();
        list.add(sumNumberExpect);
        list.add(sumNumberReal);

        List<Colleges> collegesList = baseMapper.selectList(wrapper);
        for (Colleges colleges : collegesList) {
            Integer numberExpect = colleges.getNumberExpect();
            Integer numberReal = colleges.getNumberReal();

            sumNumberExpect += numberExpect;
            sumNumberReal += numberReal;
        }
        return list;
    }

    @Override
    public List<Colleges> getAllName() {
        //得到全部校区并去重
        QueryWrapper<Colleges> wrapper = new QueryWrapper<>();
        wrapper.select("DISTINCT name");
        List<Colleges> list = baseMapper.selectList(wrapper);
        return list;
    }

    @Override
    public Map<String, Object> showChart(CollegesVo collegesVo) {

        QueryWrapper<Colleges> wrapper = new QueryWrapper<>();
        wrapper.eq("name", collegesVo.getName());
        wrapper.eq("campusId", collegesVo.getCampusId());
        wrapper.between("statistical_year", collegesVo.getBegin(), collegesVo.getEnd());

        List<Colleges> collegesList = baseMapper.selectList(wrapper);

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
        for (Colleges colleges : collegesList) {

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
    public boolean isSameName(String collegeName, int yearInt) {

        QueryWrapper<Colleges> wrapper = new QueryWrapper<>();
        wrapper.eq("name", collegeName);
        wrapper.eq("statistical_year", yearInt);

        Integer count = baseMapper.selectCount(wrapper);

        return count > 0;
    }

    @Override
    public List<Colleges> getCollegeByCampusId(String campusId) {
        QueryWrapper<Colleges> wrapper = new QueryWrapper<>();
        wrapper.eq("campus_id", campusId);

        List<Colleges> colleges = baseMapper.selectList(wrapper);
        return colleges;
    }

    @Override
    public List<Colleges> getCollege(CollegesVo collegesVo) {
        QueryWrapper<Colleges> wrapper = new QueryWrapper<>();

        QueryWrapper<Campus> campusQueryWrapper = new QueryWrapper<>();
        campusQueryWrapper.between("statistical_year", collegesVo.getBegin(), collegesVo.getEnd());
        campusQueryWrapper.eq("name", collegesVo.getCampusName());

        List<Campus> list = campusService.list(campusQueryWrapper);
        Collection<String> CID = new ArrayList<String>();
        List<Colleges> colleges = new ArrayList<>();

        for (Campus campus : list) {
            CID.add(campus.getId());
        }

        if (list.size() > 0) {
            wrapper.between("statistical_year", collegesVo.getBegin(), collegesVo.getEnd());
            wrapper.in("campus_id", CID);

            colleges = baseMapper.selectList(wrapper);
        }

        //为空
        if (collegesVo.getBegin() == null && collegesVo.getCampusId() == null && collegesVo.getCampusName() == null && collegesVo.getEnd() == null && collegesVo.getName() == null) {
            colleges = collegesService.list(null);

        }
        return colleges;
    }
}
