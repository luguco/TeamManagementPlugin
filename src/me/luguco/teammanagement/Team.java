package me.luguco.teammanagement;

import me.luguco.teammanagement.mysql.GetDatas;
import me.luguco.teammanagement.mysql.SetDatas;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Team implements CommandExecutor {
    public Team(Main main) {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) sender.sendMessage("Du musst ein Spieler sein, um diesen Command zu benutzen");

        Player p = (Player) sender;

        if (args.length < 1) {
            p.sendMessage("§1[§9TeamManagement§1] §cNutze /team <help|register");
            return true;
        }

        if (args[0].equalsIgnoreCase("register")) {

            if (!p.hasPermission("team.register")) {
                p.sendMessage("§1[§9TeamManagement§1] §cDu darfst diesen Command nicht benutzen");
                return true;
            }

            if (args.length < 2) {
                p.sendMessage("§1[§9TeamManagement§1] §cNutze: /team register <Passwort>");
                return true;
            }

            String password = args[1];
            if (args.length > 2) {
                p.sendMessage("§1[§9TeamManagement§1] §cDein Passwort darf kein Leerzeichen enthalten");
                return true;
            }

            if(GetDatas.UserExisits(p.getUniqueId().toString())){
                p.sendMessage("§1[§9TeamManagement§1] §cDu hast dich bereits registriert");
                return true;
            }
            SetDatas.Register(p.getName(), p.getUniqueId().toString(), password);
            p.sendMessage("§1[§9TeamManagement§1] §aDu wurdest erfolgreich registriert");
            return true;
        }
        return true;
    }
}
