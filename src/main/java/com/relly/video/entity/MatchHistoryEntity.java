package com.relly.video.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class MatchHistoryEntity implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column match_history.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column match_history.startTime
     *
     * @mbg.generated
     */
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column match_history.user1
     *
     * @mbg.generated
     */
    private Integer user1;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column match_history.user2
     *
     * @mbg.generated
     */
    private Integer user2;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column match_history.endTime
     *
     * @mbg.generated
     */
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;


    private Integer otherUserId;

    private String otherUserPhone;

    private Integer currentUserId;

    private String currentUserPhone;

    public Integer getOtherUserId() {
        return otherUserId;
    }

    public void setOtherUserId(Integer otherUserId) {
        this.otherUserId = otherUserId;
    }

    public String getOtherUserPhone() {
        return otherUserPhone;
    }

    public void setOtherUserPhone(String otherUserPhone) {
        this.otherUserPhone = otherUserPhone;
    }

    public Integer getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(Integer currentUserId) {
        this.currentUserId = currentUserId;
    }

    public String getCurrentUserPhone() {
        return currentUserPhone;
    }

    public void setCurrentUserPhone(String currentUserPhone) {
        this.currentUserPhone = currentUserPhone;
    }

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table match_history
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column match_history.id
     *
     * @return the value of match_history.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column match_history.id
     *
     * @param id the value for match_history.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column match_history.startTime
     *
     * @return the value of match_history.startTime
     *
     * @mbg.generated
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column match_history.startTime
     *
     * @param startTime the value for match_history.startTime
     *
     * @mbg.generated
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column match_history.user1
     *
     * @return the value of match_history.user1
     *
     * @mbg.generated
     */
    public Integer getUser1() {
        return user1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column match_history.user1
     *
     * @param user1 the value for match_history.user1
     *
     * @mbg.generated
     */
    public void setUser1(Integer user1) {
        this.user1 = user1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column match_history.user2
     *
     * @return the value of match_history.user2
     *
     * @mbg.generated
     */
    public Integer getUser2() {
        return user2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column match_history.user2
     *
     * @param user2 the value for match_history.user2
     *
     * @mbg.generated
     */
    public void setUser2(Integer user2) {
        this.user2 = user2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column match_history.endTime
     *
     * @return the value of match_history.endTime
     *
     * @mbg.generated
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column match_history.endTime
     *
     * @param endTime the value for match_history.endTime
     *
     * @mbg.generated
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}