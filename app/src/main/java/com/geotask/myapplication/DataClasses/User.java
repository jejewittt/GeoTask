package com.geotask.myapplication.DataClasses;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.TypeConverters;

import com.geotask.myapplication.Controllers.Helpers.EmailConverter;
import com.geotask.myapplication.Controllers.Helpers.ListConverter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * data structure for A User
 * keeps track of Id for login
 * their login name and contact information
 * email is unique
 */
@Entity(tableName = "users")
public class User extends GTData{
    @ColumnInfo(name = "name", typeAffinity = ColumnInfo.TEXT)
    private String name;
    @ColumnInfo(name = "email", typeAffinity = ColumnInfo.TEXT)
    private String email;
    @ColumnInfo(name = "phonenum", typeAffinity = ColumnInfo.TEXT) //ToDo validation
    private String phonenum;
    @ColumnInfo
    private byte[] userPhoto;
    @ColumnInfo
    private Integer completedTasks; //metric of completed tasks
    @ColumnInfo(name = "location", typeAffinity = ColumnInfo.TEXT)
    private String location;                                        //format example: "47.55,-82.11"
    @TypeConverters(ListConverter.class)
    private ArrayList<String> historyList;
    @TypeConverters(ListConverter.class)
    private ArrayList<String> starredList;
    @TypeConverters(ListConverter.class)
    private ArrayList<String> notificationList;

    /**
     *constructor
     * @param userPhoto
     * @param name
     * @param email
     * @param phonenum
     */
    public User(byte[] userPhoto,String name, String email, String phonenum){
        super.setType(User.class.toString());
        this.userPhoto = userPhoto;
        this.name = name;
        this.email = EmailConverter.convertEmailForElasticSearch(email);
        this.phonenum = phonenum;
        this.completedTasks = 0;
        super.setDate(new Date().getTime());
        this.starredList = new ArrayList<String>();
        this.historyList = new ArrayList<String>();
    }

    @Ignore
    public User(String name, String email, String phonenum){
        super.setType(User.class.toString());
        this.userPhoto = userPhoto;
        this.name = name;
        this.email = EmailConverter.convertEmailForElasticSearch(email);
        this.phonenum = phonenum;
        this.completedTasks = 0;
        super.setDate(new Date().getTime());
        this.starredList = new ArrayList<String>();
        this.historyList = new ArrayList<String>();
    }



    /**
     *gets  the location of user
     *@return location
     */
    public String getLocation() {
        return location;
    }

    /**
     *gets the name of the user
     * @return name
     */
    public String getName(){
        return name;
    }
    /**
     *gets email email is converted into a nice way for elasticseach to handle
     * @return EmailConverter.revertEmailFromElasticSearch(email)
     */
    public String getEmail(){
        return EmailConverter.revertEmailFromElasticSearch(email);
    }

    /**
     * sets email. converted into a good way for elasticsearch to handle
     * @param email
     */
    public void setEmail(String email){
        this.email = EmailConverter.convertEmailForElasticSearch(email);
    }

    /**
     * retunrs phonenumber
     * @return
     */
    public String getPhonenum(){
        return phonenum;
    }

    /**
     * retunrs x coord for location
     * @return Float.parseFloat(location.split("[,]")[0])
     */
    public float getLocationX() { return Float.parseFloat(location.split("[,]")[0]); }

    /**
     * retunrs y coord for location
     * @return Float.parseFloat(location.split("[,]")[0])
     */
    public float getLocationY() { return Float.parseFloat(location.split("[,]")[1]); }

    /**
     * sets the loaction
     * @param location
     */
    public void setLocation(String location) { this.location = location; }

    /**
     * sets the phonenumber
     * @param phonenum
     */
    public void setPhonenum(String phonenum){
        this.phonenum = phonenum;
    }

    /**
     * sets the name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *gets a number of completed tasks
     */

    public byte[] getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(byte[] userPhoto) {
        this.userPhoto = userPhoto;
    }
    public Integer getCompletedTasks() {
        return completedTasks;
    }

    /**
     *sets ammount of completed tasks
     * @param completedTasks
     */
    public void setCompletedTasks(Integer completedTasks) {
        this.completedTasks = completedTasks;
    }

    /**
     *increments the ammount of completed tasks
     */
    public void incrementCompletedTasks() {
        this.completedTasks++;
    }

    /**
     *returns the name email and phonenumber in string format
     * again email is converted back to normal form
     * @return this.name + " " +
    EmailConverter.revertEmailFromElasticSearch(email) + " " +
    this.phonenum;
     */
    public String toString()  {
        return this.name + " " +
                EmailConverter.revertEmailFromElasticSearch(email) + " " +
                this.phonenum;
    }

    /**
     * Method to return the list of starred tasks
     *
     * @return - the list of starred tasks
     */
    public ArrayList<String> getStarredList() {
        return starredList;
    }

    /**
     * Method to set the starred list
     *
     * @param starredList the list of new starred tasks
     */
    public void setStarredList(ArrayList<String> starredList) {
        this.starredList = starredList;
    }

    /**
     * Method to get the history list
     *
     * @return - the list of history list
     */
    public ArrayList<String> getHistoryList() {
        return historyList;
    }

    /**
     * Method to set the history list
     *
     * @param historyList the list of new history tasks
     */
    public void setHistoryList(ArrayList<String> historyList) {
        this.historyList = historyList;
    }

    public ArrayList<String> getNotificationList() {
        return notificationList;
    }

    public void setNotificationList(ArrayList<String> notificationList) {
        this.notificationList = notificationList;
    }
}
