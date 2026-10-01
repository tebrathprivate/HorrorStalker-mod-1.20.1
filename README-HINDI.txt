HORROR STALKER - Fabric 1.20.1

Is folder ko build karne ke liye:
1) Windows PC par Java 17+ hona chahiye.
2) BUILD-MOD.bat par double-click karo.
3) Pehli baar internet chahiye, kyunki Gradle/Fabric dependencies download hongi.
4) Build complete hone par:
   build\libs\horrorstalker-1.0.0.jar
   milegi.
5) Is JAR ko .minecraft\mods me daalo.
6) Fabric 1.20.1 + Fabric API ke saath Minecraft chalao.

Test:
  /summon horrorstalker:whistler
  /summon horrorstalker:daddy_in_red

Mod features:
- The Whistler
- Daddy in Red
- 128-block follow/detection range
- Chase + melee AI
- Night-only random stalking events
- Rare Darkness + Warden heartbeat event
- Night me distant horror spawn
- Supplied Blockbench model geometry adapted
- Supplied walk animation timing ko manual walk animation me adapt kiya gaya

IMPORTANT TEXTURE NOTE:
Tumhari di hui PNG files transparent character renders hain, normal Blockbench UV texture atlases nahi.
Isliye project me temporary/generated atlases banaye gaye hain: face ko head area me use kiya gaya hai aur body dark/red fill ki gayi hai.
Agar tum Blockbench ki actual UV texture PNG files doge, unko directly replace karke exact skin/texture mapping ki ja sakti hai.
