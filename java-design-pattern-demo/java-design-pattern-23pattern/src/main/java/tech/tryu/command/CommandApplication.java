package tech.tryu.command;

import tech.tryu.command.command.ActalOperator;
import tech.tryu.command.command.Sender;
import tech.tryu.command.command.command.ConcreteCommand;

/**
 * 命令模式: 感觉没啥用啊，就是
 *
 * @author YU
 * @date 2022-05-05 11:03
 */
public class CommandApplication {
    public static void main(String[] args) {
        ConcreteCommand printCommand = new ConcreteCommand(new ActalOperator(),"one");
        Sender sender = new Sender();
        sender.setCommand(printCommand);
        sender.execute();
    }
}
