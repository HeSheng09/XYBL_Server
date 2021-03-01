package com.xybl.server.dao;

import com.xybl.server.entity.Appeal;
import com.xybl.server.entity.Research;

import java.util.List;
import java.util.Map;

/**
 * ResearchDao
 * <p></p>
 * @author hesheng
 * @create 2021/3/1
 **/
public interface ResearchDao {
    /**
    * getOneResearchById
    * <p>按ID查询一条research。</p>
    * @param id java.lang.String.
    * @return com.xybl.server.entity.Research
    * @author hesheng
    * @create: 2021/3/1
    */
    public Research getOneResearchById(String id);

    /**
    * getResearches
    * <p>查询多条Research（最多100条）。</p>
    * @param  .
    * @return java.util.List<com.xybl.server.entity.Research>
    * @author hesheng
    * @create: 2021/3/1
    */
    public List<Research> getResearches();

    /**
    * addOneResearch
    * <p>添加一条Research。</p>
    * @param research com.xybl.server.entity.Research.
    * @return void
    * @author hesheng
    * @create: 2021/3/1
    */
    public void addOneResearch(Research research);

    /**
    * deleteOneResearchById
    * <p>根据id删除一条Research。</p>
    * @param id java.lang.String.
    * @return void
    * @author hesheng
    * @create: 2021/3/1
    */
    public void deleteOneResearchById(String id);

    /**
    * updateOneResearch
    * <p>根据ID修改指定Research内容。</p>
    * @param research com.xybl.server.entity.Research.
    * @return void
    * @author hesheng
    * @create: 2021/3/1
    */
    public void updateOneResearch(Research research);

    /**
    * getLastId
    * <p>查找上一条Research ID。</p>
    * @param  .
    * @return java.util.Map<java.lang.String,java.lang.Object>
    * @author hesheng
    * @create: 2021/3/1
    */
    public Map<String,Object> getLastId();
}
