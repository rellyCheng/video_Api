package com.relly.video.dao;

import com.relly.video.entity.MatchHistoryEntity;

public interface MatchHistoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table match_history
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table match_history
     *
     * @mbg.generated
     */
    int insert(MatchHistoryEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table match_history
     *
     * @mbg.generated
     */
    int insertSelective(MatchHistoryEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table match_history
     *
     * @mbg.generated
     */
    MatchHistoryEntity selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table match_history
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(MatchHistoryEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table match_history
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(MatchHistoryEntity record);
}