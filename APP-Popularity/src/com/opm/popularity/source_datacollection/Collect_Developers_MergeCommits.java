/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opm.popularity.source_datacollection;

import com.opm.popularity.core.Coversions;
import com.opm.popularity.excel_.Create_ExcelFile;
import com.opm.popularity.core.File_Details;
import com.opm.popularity.core.MathsFunctions;
import com.opm.popularity.excel_.ReadExcelFile_1Column;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author john
 */
public class Collect_Developers_MergeCommits {

    public static void main(String[] args) throws Exception {
        stats();
    }

    private static void stats() throws Exception {
        DecimalFormat newFormat = new DecimalFormat("#.##");
        Object[] datas = null;
        //String file_stats = "Repos.xlsx";
        String file_googleplay0 = "googleplayplay_com_dev_cd.xlsx";
        String file_googleplay1 = "repos_gp_combshaa1_500.xlsx";
        String file_googleplay2 = "repos_gp_combshaa11.xlsx";
        String file_googleplay3 = "repos_gp_combshaa2.xlsx";
        String file_googleplay4 = "repos_gp_combshaa3.xlsx";
        String file_googleplay5 = "repos_gp_combshaa1_500.xlsx";
        //String file_collect2 = "Commits_Cleared_500-1000.xlsx";
        String path_googleplay = "/Users/john/Desktop/";
        //String path_stats = "/Users/john/Desktop/Dev_Commits/";
        String path_new = "/Users/john/Desktop/Dev_Commits/00New_Repos/files_packages/00googleplayplay/merged/";
        //todo:
        String[] files_googleplay = {file_googleplay0};

        for (int a = 0; a < files_googleplay.length; a++) {
            int sheet_index = 0;
            int numbers = File_Details.getWorksheets(path_googleplay + files_googleplay[a]);
            //System.out.println("Reading Collection Excel....!");
            while (sheet_index < numbers) {
                ArrayList< Object[]> allobj = new ArrayList<Object[]>();
                datas = new Object[]{"Project", "forks", "Commits", "Unique", "Commits", "Percentage", "Category", "Major", "Minor", "Total","","Changes", "Percentage", "Category", "Major", "Minor", "Total"};
                allobj.add(datas);

                String project = File_Details.setProjectName(path_googleplay + files_googleplay[a], sheet_index, "B2");
                List<String> nameList = ReadExcelFile_1Column.readColumnAsString(path_googleplay + files_googleplay[a], sheet_index, 2, 2);
                List<Double> commits = ReadExcelFile_1Column.readColumnAsNumeric(path_googleplay + files_googleplay[a], sheet_index, 3, 2);
                List<Double> unique = ReadExcelFile_1Column.readColumnAsNumeric(path_googleplay + files_googleplay[a], sheet_index, 4, 2);
                List<String> commits_ = ReadExcelFile_1Column.readColumnAsString(path_googleplay + files_googleplay[a], sheet_index, 6, 1);
                List<String> contribution = ReadExcelFile_1Column.readColumnAsString(path_googleplay + files_googleplay[a], sheet_index, 7, 1);
                List<String> percentage = ReadExcelFile_1Column.readColumnAsString(path_googleplay + files_googleplay[a], sheet_index, 8, 1);
                List<String> category = ReadExcelFile_1Column.readColumnAsString(path_googleplay + files_googleplay[a], sheet_index, 7, 1);

                List<List<String>> DataCollection = new ArrayList<>();
                List<List<String>> DataCollection2 = new ArrayList<>();
                for (int i = 0; i < contribution.size(); i++) {
                    String[] splits = contribution.get(i).split("/");
                    String[] splits2 = commits_.get(i).split("/");
                    List<String> list = new ArrayList<>();
                    List<String> list2 = new ArrayList<>();
                    for (int j = 0; j < splits.length; j++) {
                        list.add(splits[j]);
                        list2.add(splits2[j]);
                    }
                    DataCollection.add(list);
                    DataCollection2.add(list2);
                }
                String name_i = "", name_j = "", email_prefix_i = "", email_prefix_j = "", login_i = "", login_j = "", location_i = "", location_j = "", created_at_i = "", created_at_j = "", date_i = "", date_j = "";
                String contrib_i = "", contrib_j = "";

                List<String> contList = new ArrayList<>();

                for (int i = 0; i < DataCollection.size(); i++) {
                    //System.out.println(i+"\t"+contribution.get(i));
                    // contrib_i = contribution.get(i);
                    //String[] split_i = contribution.get(i).split("/");
                    date_i = percentage.get(i);
                    List<String> lists_i = DataCollection.get(i);
                    List<String> lists2_i = DataCollection2.get(i);
                    // name_i = 
                    for (int x = 0; x < lists_i.size(); x++) {
                        name_i = lists_i.get(x).split("\\|")[0];
                        //System.out.println(split_i[x]+"\t"+split_i.length);
                        if (lists_i.get(x).split("\\|").length > 1) {

                            if (lists_i.get(x).split("\\|")[1].contains("@")) {
                                email_prefix_i = lists_i.get(x).split("\\|")[1].substring(0, lists_i.get(x).split("\\|")[1].indexOf("@"));
                            }

                            login_i = lists_i.get(x).split("\\|")[2].split(":")[0];
                        } else {
                            email_prefix_i = "em##";
                            login_i = "###";
                        }
                        for (int j = 0; j < DataCollection.size(); j++) {
                            //contrib_j = contribution.get(j);
                            List<String> lists_j = DataCollection.get(j);
                            List<String> lists2_j = DataCollection2.get(j);
                            //String[] split_j = contribution.get(j).split("/");
                            date_j = percentage.get(j);
                            for (int y = 0; y < lists_j.size(); y++) {
                                name_j = lists_j.get(y).split("\\|")[0];
                                if (lists_j.get(y).split("\\|").length > 1) {

                                    //System.out.println(lists_j.size()+"\t"+y);
                                    //System.out.println(name_i+"\t"+email_prefix_i+"\t"+login_i);
                                    ///System.out.println(name_j+"\t"+email_prefix_j+"\t"+login_j);
                                    if (lists_j.get(y).split("\\|")[1].contains("@")) {
                                        email_prefix_j = lists_j.get(y).split("\\|")[1].substring(0, lists_j.get(y).split("\\|")[1].indexOf("@"));
                                    }
                                    login_j = lists_j.get(y).split("\\|")[2].split(":")[0];

                                } else {
                                    email_prefix_j = "em2###";
                                    login_j = "log2###";
                                }
                                //todo::::: .........
                                String str1 = "", commits1 = "";
                                String str2 = "", commits2 = "";

                                String str_1 = "", commits_1 = "";
                                String str_2 = "", commits_2 = "";

                                String str3 = "", commits3 = "";
                                //
                                if (email_prefix_i.equals(email_prefix_j) && !login_i.equals("login######") && login_j.equals("login######")) {// || name_i.equals(name_j) || email_prefix_i.equals(email_prefix_j)){
                                    str1 = lists_i.get(x).substring(0, lists_i.get(x).lastIndexOf(":"));
                                    str2 = lists_j.get(y).substring(0, lists_j.get(y).lastIndexOf(":"));

                                    str_1 = lists2_i.get(x).substring(0, lists2_i.get(x).lastIndexOf(":"));
                                    str_2 = lists2_j.get(y).substring(0, lists2_j.get(y).lastIndexOf(":"));

                                    if (str1.length() > 5 && str2.length() > 5) {
                                        String com_i = lists_i.get(x).substring(lists_i.get(x).lastIndexOf(":") + 1, lists_i.get(x).length());
                                        String com_j = lists_j.get(y).substring(lists_j.get(y).lastIndexOf(":") + 1, lists_j.get(y).length());

                                        String com2_i = lists2_i.get(x).substring(lists2_i.get(x).lastIndexOf(":") + 1, lists2_i.get(x).length());
                                        String com2_j = lists2_j.get(y).substring(lists2_j.get(y).lastIndexOf(":") + 1, lists2_j.get(y).length());

                                        if (date_i.equals(date_j)) {
                                            double com = 0;
                                            double com2 = 0;
                                            if (Coversions.isDouble(com_i) && Coversions.isDouble(com_j)) {
                                                com = Double.parseDouble(com_i) + Double.parseDouble(com_j);
                                            }

                                            if (Coversions.isDouble(com2_i) && Coversions.isDouble(com2_j)) {
                                                com2 = Double.parseDouble(com2_i) + Double.parseDouble(com2_j);
                                            }
                                            //contrib_i = contrib_i.replaceAll(split_i[x], str1 + ":" + com);
                                            /// Delete from the list..
                                            //contrib_i = contrib_i.replaceAll(split_j[y], "");
                                            //System.out.println(lists_i + "\t" + split_i[x]);
                                            int index = lists_i.indexOf(lists_i.get(x));
                                            if (index != -1) {
                                                lists_i.set(index, str1 + ":" + com);
                                                lists_i.remove(lists_i.indexOf(lists_j.get(y)));
                                                DataCollection.set(i, lists_i);

                                                lists2_i.set(index, str_1 + ":" + com2);
                                                lists2_i.remove(lists2_i.indexOf(lists2_j.get(y)));
                                                DataCollection2.set(i, lists2_i);
                                                //DataCollection.remove(lists_j);
                                            }
                                            //System.out.println("1 : " + contribution.get(i) + "\t" + split_i[x] + "\t" + str1 + ":" + com);
                                            //System.out.println("1... : " + contribution.get(i) + "\t" + split_j[y]);

                                        } else {
                                            //contrib_j = contrib_j.replaceAll(split_j[y], str1 + com_j);
                                            //System.out.println(lists_j+"\t"+split_j[y]);

                                            int index = lists_j.indexOf(lists_j.get(y));
                                            if (index != -1) {
                                                lists_j.set(index, str1 + ":" + com_j);

                                                DataCollection.set(j, lists_j);

                                                lists2_j.set(index, str_1 + ":" + com2_j);
                                                DataCollection2.set(j, lists2_j);
                                            }

                                            //System.err.println("11:  " + contribution.get(i) + "\t" + split_i[x]);
                                        }
                                    }
                                    //System.out.println("1: "+login_i+" , "+login_j);
                                } else if (email_prefix_i.equals(email_prefix_j) && !login_j.equals("login######") && login_i.equals("login######")) {
                                    str1 = lists_i.get(x).substring(0, lists_i.get(x).lastIndexOf(":"));
                                    str2 = lists_j.get(y).substring(0, lists_j.get(y).lastIndexOf(":"));

                                    str_1 = lists2_i.get(x).substring(0, lists2_i.get(x).lastIndexOf(":"));
                                    str_2 = lists2_j.get(y).substring(0, lists2_j.get(y).lastIndexOf(":"));

                                    if (str1.length() > 5 && str2.length() > 5) {
                                        String com_i = lists_i.get(x).substring(lists_i.get(x).lastIndexOf(":") + 1, lists_i.get(x).length());
                                        String com_j = lists_j.get(y).substring(lists_j.get(y).lastIndexOf(":") + 1, lists_j.get(y).length());

                                        String com2_i = lists2_i.get(x).substring(lists2_i.get(x).lastIndexOf(":") + 1, lists2_i.get(x).length());
                                        String com2_j = lists2_j.get(y).substring(lists2_j.get(y).lastIndexOf(":") + 1, lists2_j.get(y).length());

                                        if (date_i.equals(date_j)) {
                                            double com = 0;
                                            double com2 = 0;
                                            if (Coversions.isDouble(com_i) && Coversions.isDouble(com_j)) {
                                                com = Double.parseDouble(com_i) + Double.parseDouble(com_j);
                                            }

                                            if (Coversions.isDouble(com2_i) && Coversions.isDouble(com2_j)) {
                                                com2 = Double.parseDouble(com2_i) + Double.parseDouble(com2_j);
                                            }
                                            //contrib_i = contrib_i.replaceAll(lists_i.get(x), str2 + ":" + com);
                                            /// Delete from the list..
                                            //contrib_i = contrib_i.replaceAll(lists_j.get(y), "");
                                            int index = lists_i.indexOf(lists_i.get(x));
                                            if (index != -1) {
                                                lists_i.set(index, str2 + ":" + com);
                                                lists_i.remove(lists_i.indexOf(lists_j.get(y)));
                                                DataCollection.set(i, lists_i);

                                                lists2_i.set(index, str_2 + ":" + com2);
                                                lists2_i.remove(lists2_i.indexOf(lists2_j.get(y)));
                                                DataCollection2.set(i, lists2_i);

                                                //DataCollection.remove(lists_j);
                                            }

                                            //System.err.println("2 : " + contribution.get(i) + "\t" + split_i[x] + "\t" + com);
                                        } else {
                                            //contrib_i = contrib_i.replaceAll(split_i[x], str2 + ":" + com_i);

                                            int index = lists_i.indexOf(lists_i.get(x));
                                            if (index != -1) {
                                                lists_i.set(index, str2 + ":" + com_i);
                                                DataCollection.set(j, lists_i);

                                                lists2_i.set(index, str_2 + ":" + com2_i);
                                                DataCollection2.set(j, lists2_i);
                                            }
                                            //System.err.println("21 : " + contribution.get(i) + "\t" + split_i[x]);
                                        }
                                    }
                                    //System.out.println("4: "+login_i+" , "+login_j);

                                } else if (name_i.equals(name_j) && !login_i.equals("login######") && login_j.equals("login######")) {
                                    str1 = lists_i.get(x).substring(0, lists_i.get(x).lastIndexOf(":"));
                                    str2 = lists_j.get(y).substring(0, lists_j.get(y).lastIndexOf(":"));

                                    str_1 = lists2_i.get(x).substring(0, lists2_i.get(x).lastIndexOf(":"));
                                    str_2 = lists2_j.get(y).substring(0, lists2_j.get(y).lastIndexOf(":"));

                                    String com_i = lists_i.get(x).substring(lists_i.get(x).lastIndexOf(":") + 1, lists_i.get(x).length());
                                    String com_j = lists_j.get(y).substring(lists_j.get(y).lastIndexOf(":") + 1, lists_j.get(y).length());

                                    String com2_i = lists2_i.get(x).substring(lists2_i.get(x).lastIndexOf(":") + 1, lists2_i.get(x).length());
                                    String com2_j = lists2_j.get(y).substring(lists2_j.get(y).lastIndexOf(":") + 1, lists2_j.get(y).length());

                                    if (str1.length() > 5 && str2.length() > 5) {
                                        if (date_i.equals(date_j)) {
                                            double com = 0;
                                            double com2 = 0;
                                            if (Coversions.isDouble(com_i) && Coversions.isDouble(com_j)) {
                                                com = Double.parseDouble(com_i) + Double.parseDouble(com_j);
                                            }

                                            if (Coversions.isDouble(com2_i) && Coversions.isDouble(com2_j)) {
                                                com2 = Double.parseDouble(com2_i) + Double.parseDouble(com2_j);
                                            }
                                            //contrib_i = contrib_i.replaceAll(split_i[x], str1 + ":" + com);
                                            /// Delete from the list..
                                            //contrib_i = contrib_i.replaceAll(split_j[y], "");
                                            //System.err.println("3 : " + contribution.get(i) + "\t" + split_i[x] + "\t" + com);
                                            int index = lists_i.indexOf(lists_i.get(x));
                                            if (index != -1) {
                                                lists_i.set(index, str1 + ":" + com);
                                                lists_i.remove(lists_i.indexOf(lists_j.get(y)));
                                                DataCollection.set(i, lists_i);

                                                lists2_i.set(index, str_1 + ":" + com2);
                                                lists2_i.remove(lists2_i.indexOf(lists2_j.get(y)));
                                                DataCollection2.set(i, lists2_i);
                                                //DataCollection.remove(lists_j);
                                            }
                                        } else {

                                            //contrib_j = contrib_j.replaceAll(split_j[y], str1 + ":" + com_j);
                                            int index = lists_j.indexOf(lists_j.get(y));
                                            if (index != -1) {
                                                lists_j.set(index, str1 + ":" + com_j);
                                                DataCollection.set(j, lists_j);

                                                lists2_j.set(index, str_1 + ":" + com2_j);
                                                DataCollection2.set(j, lists2_j);
                                            }
                                            /// System.err.println("31 : " + contribution.get(j) + "\t" + split_j[y]);
                                        }
                                    }

                                    /////System.out.println("5: "+login_i+" , "+login_j);
                                } else if (name_i.equals(name_j) && !login_j.equals("login######") && login_i.equals("login######")) {
                                    str1 = lists_i.get(x).substring(0, lists_i.get(x).lastIndexOf(":"));
                                    str2 = lists_j.get(y).substring(0, lists_j.get(y).lastIndexOf(":"));

                                    str_1 = lists2_i.get(x).substring(0, lists2_i.get(x).lastIndexOf(":"));
                                    str_2 = lists2_j.get(y).substring(0, lists2_j.get(y).lastIndexOf(":"));

                                    if (str1.length() > 5 && str2.length() > 5) {
                                        String com_i = lists_i.get(x).substring(lists_i.get(x).lastIndexOf(":") + 1, lists_i.get(x).length());
                                        String com_j = lists_j.get(y).substring(lists_j.get(y).lastIndexOf(":") + 1, lists_j.get(y).length());

                                        String com2_i = lists2_i.get(x).substring(lists2_i.get(x).lastIndexOf(":") + 1, lists2_i.get(x).length());
                                        String com2_j = lists2_j.get(y).substring(lists2_j.get(y).lastIndexOf(":") + 1, lists2_j.get(y).length());

                                        if (date_i.equals(date_j)) {
                                            double com = 0;
                                            double com2 = 0;
                                            if (Coversions.isDouble(com_i) && Coversions.isDouble(com_j)) {
                                                com = Double.parseDouble(com_i) + Double.parseDouble(com_j);
                                            }

                                            if (Coversions.isDouble(com2_i) && Coversions.isDouble(com2_j)) {
                                                com2 = Double.parseDouble(com2_i) + Double.parseDouble(com2_j);
                                            }

                                            //contrib_i = contrib_i.replaceAll(split_i[x], str2 + ":" + com);
                                            /// Delete from the list..
                                            //contrib_i = contrib_i.replaceAll(split_j[y], "");
                                            int index = lists_i.indexOf(lists_i.get(x));
                                            if (index != -1) {
                                                lists_i.set(index, str2 + ":" + com);
                                                lists_i.remove(lists_i.indexOf(lists_j.get(y)));
                                                DataCollection.set(i, lists_i);

                                                lists2_i.set(index, str_2 + ":" + com2);
                                                lists2_i.remove(lists2_i.indexOf(lists2_j.get(y)));
                                                DataCollection2.set(i, lists2_i);
                                                //DataCollection.remove(lists_j);
                                            }
                                            //System.err.println("4 : " + contribution.get(i) + "\t" + split_i[x] + "\t" + com);
                                        } else {

                                            //contrib_i = contrib_i.replaceAll(split_i[x], str2 + ":" + com_i);
                                            int index = lists_i.indexOf(lists_i.get(x));
                                            if (index != -1) {
                                                lists_i.set(index, str2 + ":" + com_i);
                                                DataCollection.set(j, lists_i);

                                                lists2_i.set(index, str_2 + ":" + com2_i);
                                                DataCollection2.set(j, lists2_i);
                                            }
                                            // System.err.println("41 : " + contribution.get(i) + "\t" + split_i[x]);
                                        }
                                    }
                                    //		
                                } else if (email_prefix_i.equals(email_prefix_j) && login_i.equals(login_j) && x != y) {
                                    //System.out.println(lists_i.size() + " \t" + x);
                                    if (lists_i.size() < x && lists_j.size() < y) {
                                        str1 = lists_i.get(x).substring(0, lists_i.get(x).lastIndexOf(":"));
                                        str2 = lists_j.get(y).substring(0, lists_j.get(y).lastIndexOf(":"));

                                        str_1 = lists2_i.get(x).substring(0, lists2_i.get(x).lastIndexOf(":"));
                                        str_2 = lists2_j.get(y).substring(0, lists2_j.get(y).lastIndexOf(":"));

                                        //System.out.println(lists_i);
                                        //System.out.println(lists_j);
                                        if (str1.length() > 5 && str2.length() > 5) {
                                            String com_i = lists_i.get(x).substring(lists_i.get(x).lastIndexOf(":") + 1, lists_i.get(x).length());
                                            String com_j = lists_j.get(y).substring(lists_j.get(y).lastIndexOf(":") + 1, lists_j.get(y).length());

                                            String com2_i = lists2_i.get(x).substring(lists2_i.get(x).lastIndexOf(":") + 1, lists2_i.get(x).length());
                                            String com2_j = lists2_j.get(y).substring(lists2_j.get(y).lastIndexOf(":") + 1, lists2_j.get(y).length());

                                            if (date_i.equals(date_j)) {
                                                double com = 0;
                                                double com2 = 0;
                                                if (Coversions.isDouble(com_i) && Coversions.isDouble(com_j)) {
                                                    com = Double.parseDouble(com_i) + Double.parseDouble(com_j);
                                                }

                                                if (Coversions.isDouble(com2_i) && Coversions.isDouble(com2_j)) {
                                                    com2 = Double.parseDouble(com2_i) + Double.parseDouble(com2_j);
                                                }

                                                //contribution.set(i, str1 + ":" + com);
                                                //contrib_i = contrib_i.replaceAll(split_i[x], str1 + ":" + com);
                                                //System.err.println("5 : " + contribution.get(i) + "\t" + split_i[x] + "\t" + com);
                                                /// Delete from the list..
                                                //contrib_i = contrib_i.replaceAll(split_i[x], "");
                                                int index = lists_i.indexOf(lists_i.get(x));
                                                if (index != -1) {
                                                    lists_i.set(index, str1 + ":" + com);
                                                    lists_i.remove(lists_i.indexOf(lists_j.get(y)));
                                                    DataCollection.set(i, lists_i);

                                                    lists2_i.set(index, str_1 + ":" + com2);
                                                    lists2_i.remove(lists2_i.indexOf(lists2_j.get(y)));
                                                    DataCollection2.set(i, lists2_i);
                                                    //DataCollection.remove(lists_j);
                                                }

                                            } else {

                                                //contrib_j = contrib_j.replaceAll(split_j[y], str1 + ":" + com_j);
                                                int index = lists_j.indexOf(lists_j.get(y));
                                                if (index != -1) {
                                                    lists_j.set(index, str1 + ":" + com_j);
                                                    DataCollection.set(j, lists_j);

                                                    lists2_j.set(index, str_1 + ":" + com2_j);
                                                    DataCollection2.set(j, lists2_j);
                                                }
                                            }
                                        }

                                    }
                                }

                            }
                        }
                    }

                    //contList.add(contrib_i);
                }
                
                
                
                List<Double> percL1_0 = new ArrayList<>();
                List<Double> tot_list1_0 = new ArrayList<>();
                double tot_1 = 0, tot_2 = 0;

                List<String> rList_1 = new ArrayList<>();
                List<String> rList_2 = new ArrayList<>();

                
                List<String> lists_1 = DataCollection2.get(0);
                ///String combined1 = "";
                List<Double> percL1_1 = new ArrayList<>();
                List<Double> tot_list1_1 = new ArrayList<>();
                for (int j = 0; j < lists_1.size(); j++) {
                    if (lists_1.get(j).contains(":")) {
                        tot_list1_1.add(Double.parseDouble(lists_1.get(j).substring(lists_1.get(j).lastIndexOf(":") + 1, lists_1.get(j).length())));
                        tot_1 += Double.parseDouble(lists_1.get(j).substring(lists_1.get(j).lastIndexOf(":") + 1, lists_1.get(j).length()));

                    } else {
                        rList_1.add(lists_1.get(j));
                    }
                }
                lists_1.removeAll(rList_1);

                for (int i = 0; i < tot_list1_1.size(); i++) {
                    double perc0 = (tot_list1_1.get(i) / tot_1) * 100;
                    percL1_1.add(Double.valueOf(newFormat.format(perc0)));
                }
                String login_mlp_1 = "", log_percentage1_1 = "", log_category1_1 = "";
                MathsFunctions.InsertionSort(lists_1, percL1_1, tot_list1_1);
                int max_1 = 0;
                double c_mj_1 = 0, c_mn_1 = 0;
                if (tot_list1_1.size() > 0) {
                    String cat;
                    for (int i = 0; i < tot_list1_1.size(); i++) {
                        if (max_1 <= 80) {
                            cat = "Major";
                            c_mj_1++;
                        } else {
                            c_mn_1++;
                            cat = "Minor";

                        }

                        if (lists_1.get(i).contains(":")) {
                            // System.out.println(i+":"+log_list_1.get(i)+"\t"+max1+"\t"+tot_list_1.get(i)+"\t"+cat);
                            login_mlp_1 = login_mlp_1.concat(lists_1.get(i).substring(0, lists_1.get(i).lastIndexOf(":")) + ":" + tot_list1_1.get(i) + "/");
                            log_percentage1_1 = log_percentage1_1.concat(lists_1.get(i).substring(0, lists_1.get(i).lastIndexOf(":")) + ":" + percL1_1.get(i) + "/");
                            log_category1_1 = log_category1_1.concat(lists_1.get(i).substring(0, lists_1.get(i).lastIndexOf(":")) + ":" + cat + "/");

                        }
                        max_1 += percL1_1.get(i);
                    }
                }
               
                

                String combined0 = "";
                List<Double> percL_0 = new ArrayList<>();
                List<Double> tot_list_0 = new ArrayList<>();
                double tot1 = 0, tot2 = 0;

                List<String> rList1 = new ArrayList<>();
                
                List<String> lists1 = DataCollection.get(0);
                String combined1 = "";
                List<Double> percL_1 = new ArrayList<>();
                List<Double> tot_list_1 = new ArrayList<>();
                for (int j = 0; j < lists1.size(); j++) {
                    combined1 = combined1.concat(lists1.get(j) + "/");
                    if (lists1.get(j).contains(":")) {
                        tot_list_1.add(Double.parseDouble(lists1.get(j).substring(lists1.get(j).lastIndexOf(":") + 1, lists1.get(j).length())));
                        tot1 += Double.parseDouble(lists1.get(j).substring(lists1.get(j).lastIndexOf(":") + 1, lists1.get(j).length()));

                    } else {
                        rList1.add(lists1.get(j));
                    }
                }
                lists1.removeAll(rList1);

                for (int i = 0; i < tot_list_1.size(); i++) {
                    double perc0 = (tot_list_1.get(i) / tot1) * 100;
                    percL_1.add(Double.valueOf(newFormat.format(perc0)));
                }
                String login_mlp = "", log_percentage_1 = "", log_category_1 = "";
                MathsFunctions.InsertionSort(lists1, percL_1, tot_list_1);
                int max1 = 0;
                double c_mj1 = 0, c_mn1 = 0;
                if (tot_list_1.size() > 0) {
                    String cat;
                    for (int i = 0; i < tot_list_1.size(); i++) {
                        if (max1 <= 80) {
                            cat = "Major";
                            c_mj1++;
                        } else {
                            c_mn1++;
                            cat = "Minor";

                        }

                        if (lists1.get(i).contains(":")) {
                            // System.out.println(i+":"+log_list_1.get(i)+"\t"+max1+"\t"+tot_list_1.get(i)+"\t"+cat);
                            login_mlp = login_mlp.concat(lists1.get(i).substring(0, lists1.get(i).lastIndexOf(":")) + ":" + tot_list_1.get(i) + "/");
                            log_percentage_1 = log_percentage_1.concat(lists1.get(i).substring(0, lists1.get(i).lastIndexOf(":")) + ":" + percL_1.get(i) + "/");
                            log_category_1 = log_category_1.concat(lists1.get(i).substring(0, lists1.get(i).lastIndexOf(":")) + ":" + cat + "/");

                        }
                        max1 += percL_1.get(i);
                    }
                }

                datas = new Object[]{"", project, "", "",login_mlp_1, log_percentage1_1, log_category1_1, c_mj_1, c_mn_1, c_mj_1 + c_mn_1,"", login_mlp, log_percentage_1, log_category_1, c_mj1, c_mn1, c_mj1 + c_mn1};
                allobj.add(datas);
                DataCollection.remove(0);
                DataCollection2.remove(0);
                //DataCollection.remove(0);
                
                
                

                List<String> col_list = new ArrayList<>();
                List<String> per_list = new ArrayList<>();
                List<String> cat_list = new ArrayList<>();
                List<Double> maj_list = new ArrayList<>();
                List<Double> min_list = new ArrayList<>();
                
                for (int i1 = 0; i1 < DataCollection.size(); i1++) {
                    List<String> lists = DataCollection.get(i1);
                    String combined = "";
                    List<Double> percL_2 = new ArrayList<>();
                    List<Double> tot_list_2 = new ArrayList<>();
                    List<String> rList2 = new ArrayList<>();

                    for (int j = 0; j < lists.size(); j++) {
                        combined = combined.concat(lists.get(j) + "/");
                        //System.err.println(lists.get(j).substring(lists.get(j).lastIndexOf(":") + 1, lists.get(j).length()));
                        if (lists.get(j).contains(":")) {
                            tot_list_2.add(Double.parseDouble(lists.get(j).substring(lists.get(j).lastIndexOf(":") + 1, lists.get(j).length())));
                            tot2 += Double.parseDouble(lists.get(j).substring(lists.get(j).lastIndexOf(":") + 1, lists.get(j).length()));

                        } else {
                            rList2.add(lists.get(j));
                        }
                    }
                    lists.removeAll(rList2);

                    for (int i = 0; i < tot_list_2.size(); i++) {
                        double perc0 = (tot_list_2.get(i) / tot2) * 100;
                        percL_2.add(Double.valueOf(newFormat.format(perc0)));
                    }
                    MathsFunctions.InsertionSort(lists, percL_2, tot_list_2);
                    int max2 = 0;
                    double c_mj2 = 0, c_mn2 = 0;
                    String email_collections = "", log_percentage_2 = "", log_category_2 = "";
                    if (lists.size() > 0) {
                        String cat;
                        for (int i = 0; i < tot_list_2.size(); i++) {
                            if (max2 <= 80) {
                                cat = "Major";
                                c_mj2++;
                            } else {
                                c_mn2++;
                                cat = "Minor";
                            }
                            if (lists.get(i).contains(":")) {
                                email_collections = email_collections.concat(lists.get(i).substring(0, lists.get(i).lastIndexOf(":")) + ":" + tot_list_2.get(i) + "/");
                                log_percentage_2 = log_percentage_2.concat(lists.get(i).substring(0, lists.get(i).lastIndexOf(":")) + ":" + percL_2.get(i) + "/");
                                log_category_2 = log_category_2.concat(lists.get(i).substring(0, lists.get(i).lastIndexOf(":")) + ":" + cat + "/");

                            }

                            max2 += percL_2.get(i);
                        }

                    }

                    col_list.add(email_collections);
                    per_list.add(log_percentage_2);
                    cat_list.add(log_category_2);
                    
                    maj_list.add(c_mj2);
                    min_list.add(c_mn2);
                    
                    //datas = new Object[]{"", nameList.get(i1), commits.get(i1), unique.get(i1), email_collections, log_percentage_2, log_category_2, c_mj2, c_mn2, c_mj2 + c_mn2};
                   // allobj.add(datas);

                }
                
                
                
                
                
                
                
                
                
                
                List<String> col_list2 = new ArrayList<>();
                List<String> per_list2 = new ArrayList<>();
                List<String> cat_list2 = new ArrayList<>();
                List<Double> maj_list2 = new ArrayList<>();
                List<Double> min_list2 = new ArrayList<>();
                
                for (int i1 = 0; i1 < DataCollection2.size(); i1++) {
                    List<String> lists = DataCollection2.get(i1);
                    String combined = "";
                    List<Double> percL_2 = new ArrayList<>();
                    List<Double> tot_list_2 = new ArrayList<>();
                    List<String> rList2 = new ArrayList<>();

                    for (int j = 0; j < lists.size(); j++) {
                        combined = combined.concat(lists.get(j) + "/");
                        //System.err.println(lists.get(j).substring(lists.get(j).lastIndexOf(":") + 1, lists.get(j).length()));
                        if (lists.get(j).contains(":")) {
                            tot_list_2.add(Double.parseDouble(lists.get(j).substring(lists.get(j).lastIndexOf(":") + 1, lists.get(j).length())));
                            tot_2 += Double.parseDouble(lists.get(j).substring(lists.get(j).lastIndexOf(":") + 1, lists.get(j).length()));

                        } else {
                            rList2.add(lists.get(j));
                        }
                    }
                    lists.removeAll(rList2);

                    for (int i = 0; i < tot_list_2.size(); i++) {
                        double perc0 = (tot_list_2.get(i) / tot2) * 100;
                        percL_2.add(Double.valueOf(newFormat.format(perc0)));
                    }
                    MathsFunctions.InsertionSort(lists, percL_2, tot_list_2);
                    int max2 = 0;
                    double c_mj2 = 0, c_mn2 = 0;
                    String email_collections = "", log_percentage_2 = "", log_category_2 = "";
                    if (lists.size() > 0) {
                        String cat;
                        for (int i = 0; i < tot_list_2.size(); i++) {
                            if (max2 <= 80) {
                                cat = "Major";
                                c_mj2++;
                            } else {
                                c_mn2++;
                                cat = "Minor";
                            }
                            if (lists.get(i).contains(":")) {
                                email_collections = email_collections.concat(lists.get(i).substring(0, lists.get(i).lastIndexOf(":")) + ":" + tot_list_2.get(i) + "/");
                                log_percentage_2 = log_percentage_2.concat(lists.get(i).substring(0, lists.get(i).lastIndexOf(":")) + ":" + percL_2.get(i) + "/");
                                log_category_2 = log_category_2.concat(lists.get(i).substring(0, lists.get(i).lastIndexOf(":")) + ":" + cat + "/");

                            }

                            max2 += percL_2.get(i);
                        }

                    }

                    col_list2.add(email_collections);
                    per_list2.add(log_percentage_2);
                    cat_list2.add(log_category_2);
                    
                    maj_list2.add(c_mj2);
                    min_list2.add(c_mn2);
                    
                   

                }
                for (int i = 0; i < DataCollection.size(); i++) {
                     datas = new Object[]{"", nameList.get(i), commits.get(i), unique.get(i), col_list2.get(i), per_list2.get(i), cat_list2.get(i), maj_list2.get(i), min_list2.get(i), maj_list2.get(i)+ min_list2.get(i),"",col_list.get(i), per_list.get(i), cat_list.get(i), maj_list.get(i), min_list.get(i), maj_list.get(i)+ min_list.get(i)};
                     allobj.add(datas);
                }

                String file_name = files_googleplay[a].replaceAll("googleplayplay_com_dev", "merged_com_merged_2");
                Create_ExcelFile.createExcel(allobj, 0, path_new + file_name, project.split("/")[0] + "_" + sheet_index);

                sheet_index++;
            }
        }

    }
}
