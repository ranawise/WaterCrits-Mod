<div align="center">
  <h1>WaterCrits Mod</h1>
  <p><strong>Redefining Underwater Combat in Minecraft</strong></p>
</div>

<p align="center">
  <img src="https://img.shields.io/badge/Minecraft-1.16.5%20--%201.21.x-brightgreen" alt="Minecraft Version">
  <img src="https://img.shields.io/badge/Platform-Paper%20%7C%20Spigot%20%7C%20Fabric-blue" alt="Platform">
  <img src="https://img.shields.io/badge/License-MIT-yellow" alt="License">
</p>

## Overview

Vanilla Minecraft strictly prevents critical hits while a player is submerged in water. **WaterCrits** reverses this mechanic. With this mod/plugin installed, normal melee attacks and sweep attacks are silently cancelled in water, while **critical hits are forced to be the only valid attack**. 

Whether you're running a modern Fabric client/server or a legacy Paper server, WaterCrits brings a completely new dynamic to aquatic combat.

---

## Features

- **Crit-Only Aquatic Combat**: Only perfectly timed falling attacks (critical hits) will land when submerged.
- **Sweep Attack Blocking**: Prevents AoE sweep damage while in water.
- **Cross-Platform Support**: Available as both a **Paper/Spigot Plugin** and a **Fabric Mod**.
- **Highly Configurable**: Adjust bypass permissions, block messages, and cooldown requirements.

---

## Installation

Since you are using the pre-compiled releases, installation is incredibly simple! 

### Server Plugin (Paper / Spigot 1.16+)
1. Locate the `WaterCrits-Plugin-1.0.0.jar` file in the main folder.
2. Drop the `.jar` into your server's `plugins/` folder.
3. Start or restart your server.
4. Edit the generated `plugins/WaterCrits/config.yml` if needed.
5. Apply changes instantly in-game using `/watercrits reload`.

### Fabric Mod (Minecraft 1.21.x)
1. Ensure you have [Fabric Loader](https://fabricmc.net/) and [Fabric API](https://modrinth.com/mod/fabric-api) installed.
2. Locate the `WaterCrits-Mod-1.0.0+mc1.21.11.jar` file in the main folder.
3. Drop the `.jar` into your `.minecraft/mods/` or server `mods/` folder.
4. Launch the game/server.
5. Configure the mod by editing `config/watercrits.json` and restarting.

---

## Configuration

### Plugin Configuration (`plugins/WaterCrits/config.yml`)
```yaml
block-normal-attacks: true      # Cancel non-crit hits in water
block-sweep-attacks:  true      # Cancel sweep AoE hits in water
min-cooldown-for-crit: 0.9      # 0.0–1.0; fraction of attack bar needed
allow-bypass: false             # Let watercrits.bypass perm skip rules
blocked-message: '&cOnly critical hits are allowed while in water!'
```

### Mod Configuration (`config/watercrits.json`)
```json
{
  "blockNormalAttacks": true,
  "blockSweepAttacks": true,
  "minCooldownForCrit": 0.9,
  "blockedMessage": "Only critical hits are allowed while in water!"
}
```

---

## Permissions & Commands (Plugin Only)

### Commands
| Command | Alias | Description |
|---|---|---|
| `/watercrits reload` | `/wc reload` | Reloads the configuration without a server restart |

### Permissions
| Permission | Default | Description |
|---|---|---|
| `watercrits.admin` | `op` | Allows the use of `/watercrits reload` |
| `watercrits.bypass` | `false` | Bypasses all WaterCrits restrictions in water |

---

## How It Works (Technical Details)

Vanilla Minecraft's `Player#attack()` computes the critical-hit multiplier internally and immediately sets it to 1.0 (no crit) if `isInWater()` is true. This happens before any API events are fired.

WaterCrits works around this by independently reconstructing the same conditions vanilla uses to determine a critical hit, minus the water check:
- `fallDistance > 0`
- `!onGround`
- `!onClimbable`
- `!isPassenger`
- `!hasBlindness`
- `attackCooldown >= minCooldownForCrit`

If the attacker is in water and these conditions are *not* met, the attack is cancelled at the earliest possible execution point.

---

## License

This project is licensed under the [MIT License](LICENSE).
