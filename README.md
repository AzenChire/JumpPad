![JumpPad](https://github.com/user-attachments/assets/406cab53-c76c-4631-a45a-8afc15f8765c)

# JumpPad

Step on a pressure plate to get launched forward and upward! 🚀

## 🔧 Features

- Launch players based on the direction they're looking
- Configurable launch height and multiplier
- Custom block type for jump pads (default: gold pressure plate)
- Configurable sound and message when launching
- Enable or disable each feature from `config.yml`
- Simple reload command: `/jumppad reload`
- Lightweight and easy to use

## 📥 Installation

1. Download the latest `.jar` file from the [Releases](https://github.com/AzenChire/JumpPad/releases) page
2. Drop the file into your server's `/plugins` folder
3. Start or reload your server
4. Walk on a gold pressure plate — and fly!

## ⚙️ Configuration

Here is the default `config.yml`:

```yaml
enable-sound: true
sound-name: ENTITY_FIREWORK_ROCKET_LAUNCH

enable-message: true
launch-message: "&eWhoosh!"

launch-vertical: 0.3
launch-multiplier: 3.0

launch-block: LIGHT_WEIGHTED_PRESSURE_PLATE
````

✅ [Click here for a list of Minecraft block names](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Material.html)
✅ [Click here for a list of Minecraft sounds](https://minecraft.fandom.com/wiki/Sounds.json)
✅ [Click here for Minecraft color codes](https://minecraft.fandom.com/wiki/Formatting_codes)

## 💬 Commands

| Command           | Description             |
| ----------------- | ----------------------- |
| `/jumppad reload` | Reloads the config file |

## 🌍 Language Support

* ✅ English (default)
* 🌐 More languages coming soon!

## ❓ Need Help?

Join the support Discord to report bugs, suggest features, or get help:
🔗 **[https://discord.gg/4nzzkS8beg](https://discord.gg/4nzzkS8beg)**

---

Enjoy using JumpPad! ✨
