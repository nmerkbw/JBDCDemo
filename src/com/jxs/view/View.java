package com.jxs.view;

import com.jxs.action.GirlAction;
import com.jxs.model.Girl;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by jiangxs on 2018/3/16.
 */
public class View {

    private static final String CONTEXT = "欢迎光临KB536！\n"+
            "下面是为大家介绍具有特色的女蛇精病们的功能列表~\n"+
            "[MAIN/M]:主菜单\n"+
            "[QUERY/Q]:根据名字查看女蛇精的详细信息\n"+
            "[GET/G]:根据牌号(id)查看该id的女蛇精的详细信息\n"+
            "[ADD/A]:添加女蛇精信息\n"+
            "[UPDATE/U]:更新女蛇精信息\n"+
            "[DELETE/D]:删除女蛇精及其相关信息\n"+
            "[EXIT/E]:退出女蛇精的功能列表\n"+
            "[BREAK/B]:退出当前功能，返回主菜单";

    private static final String OPERATION_MAIN = "MAIN";
    private static final String OPERATION_QUERY = "QUERY";
    private static final String OPERATION_GET = "GET";
    private static final String OPERATION_ADD = "ADD";
    private static final String OPERATION_UPDATE = "UPDATE";
    private static final String OPERATION_DELETE = "DELETE";
    private static final String OPERATION_EXIT = "EXIT";
    private static final String OPERATION_BREAK = "BREAK";


    public static void main(String[] args) {

        System.out.println(CONTEXT);
        boolean modify = false;
        String pri = OPERATION_MAIN;
        int setup = 0;
        Girl girl = new Girl();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("请选择您感兴趣的功能：");
            String in = scanner.next().toString();

            // 输入的功能为EXIT/E时退出
            if (OPERATION_EXIT.equals(in.toUpperCase())
                    || OPERATION_EXIT.substring(0,1).equals(in.toUpperCase())) {
                break;
            } else if (OPERATION_ADD.equals(in.toUpperCase())//新增
                    || OPERATION_ADD.substring(0, 1).equals(in.toUpperCase())
                    || pri.equals(OPERATION_ADD)) {
                pri = OPERATION_ADD;
                if (setup == 0) {
                    System.out.println("请输入女蛇精的名字：");
                } else if (setup == 1) {
                    girl.setUserName(in);
                    girl.setSex(1);
                    System.out.println("请输入女蛇精的年龄：");
                } else if (setup == 2) {
                    girl.setAge(Integer.parseInt(in));
                    System.out.println("请输入女蛇精的生日【生日格式如：yyyy-MM-dd】：");
                } else if (setup == 3) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date birthday = null;
                    try {
                        birthday = sdf.parse(in);
                        girl.setBirthday(birthday);
                    } catch (ParseException e) {
                        e.printStackTrace();
                        System.out.println("你搞我吧，日期格式看不懂？");
                        continue;
                    }
                    System.out.println("请输入女蛇精的邮箱：");
                } else if (setup == 4) {
                    girl.setEmail(in);
                    System.out.println("请输入女蛇精的手机号：");
                } else if (setup == 5) {
                    girl.setMobile(in);
                    girl.setCreateUser("SuperAdmin");
                    girl.setCreateDate(new Date());
                    girl.setUpdateUser("SuperAdmin");
                    girl.setUpdateDate(new Date());
                    girl.setIsdel(1);
                    GirlAction girlAction = new GirlAction();
                    try {
                        girlAction.addGirl(girl);
                        System.out.println("添加女蛇精成功，GOOD LUCK~");
                        setup = -1;
                        //pri = null;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        System.out.println("运气不好，添加蛇精失败，再试一次呢");
                    }
                }

            } else if (OPERATION_DELETE.equals(in.toUpperCase())//删除
                    || OPERATION_DELETE.substring(0, 1).equals(in.toUpperCase())
                    || pri.equals(OPERATION_DELETE)) {
                pri = OPERATION_DELETE;
                System.out.println("请输入要删除女蛇精的id：");
                int id = scanner.nextInt();
                GirlAction girlAction = new GirlAction();
                try {
                    girlAction.delteGirl(id);
                    System.out.println("删除女蛇精成功！");
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("删除女蛇精失败，请重试！");
                }
            } else if (OPERATION_UPDATE.equals(in.toUpperCase())
                    || OPERATION_UPDATE.substring(0,1).equals(in.toUpperCase())
                    || pri.equals(OPERATION_UPDATE)) {
                pri = OPERATION_UPDATE;
                if (setup == 0) {
                    System.out.println("请输入你要修改的女蛇精的id：");
                    GirlAction girlAction = new GirlAction();
                    int id = scanner.nextInt();
                    girl.setId(id);
                    try {
                        girl = girlAction.queryGirl(id);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    System.out.println("要修改女蛇精的电话号码吗？（Y/N）");
                    //setup++;
                }
                if (setup == 1) {
                    if ("N".equals(in.toUpperCase())) {
                        modify = false;
                        setup++;
                    }
                    if ("Y".equals(in.toUpperCase())) {
                        modify = true;
                        System.out.println("请输入女蛇精新的电话号码：");
                    }
                }
                if (setup == 2) {
                    if (modify) {
                        girl.setMobile(in);
                    }
                    System.out.println("要修改女蛇精的e-mail吗？（Y/N）");
                    modify = false;

                }
                if (setup == 3) {
                    if ("Y".equals(in.toUpperCase())) {
                        System.out.println("请输入女蛇精新的e-mail：");
                        modify = true;
                    }
                    if ("N".equals(in.toUpperCase())) {
                        modify = false;
                        setup++;
                    }
                }
                if (setup == 4) {
                    if (modify) {
                        girl.setEmail(in);
                    }
                    modify = false;
                    setup++;
                }
                if (setup == 5) {
                    GirlAction girlAction = new GirlAction();
                    girl.setUpdateDate(new Date());
                    girl.setIsdel(1);
                    girl.setSex(1);
                    try {
                        girlAction.updateGirl(girl);
                        System.out.println("修改女蛇精成功！");
                    } catch (SQLException e) {
                        e.printStackTrace();
                        System.out.println("修改女蛇精失败！");
                    }
                }
            } else if (OPERATION_QUERY.equals(in.toUpperCase())//查询
                        || OPERATION_QUERY.substring(0, 1).equals(in.toUpperCase())
                        || pri.equals(OPERATION_QUERY)) {
                    pri = OPERATION_QUERY;
                    System.out.println("请输入女蛇精的名字：");

                    String value = "'" + scanner.next().toString() + "'";
                    GirlAction girlAction = new GirlAction();
                    List<Map<String, Object>> parameters = new ArrayList<>();
                    Map<String, Object> parameter = new HashMap<>();
                    parameter.put("name", "user_name");
                    parameter.put("relation", "=");
                    parameter.put("value", value);
                    parameters.add(parameter);
                    try {
                        List<Girl> girls = girlAction.queryGirls(parameters);
                        for (Girl g : girls) {
                            System.out.println(g);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                        System.out.println("查询女蛇精失败！");
                    }
                } else {
                    System.out.println("您输入的指令为：" + in);
                }
            if (OPERATION_ADD.equals(pri.toUpperCase())) {
                setup++;
            }
            if (OPERATION_UPDATE.equals(pri.toUpperCase())) {
                setup++;
            }
        }

    }
}
