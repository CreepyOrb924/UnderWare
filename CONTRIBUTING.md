# Contributing

Please follow the  [Code of Conduct](https://github.com/CreepyOrb924/UnderWare/blob/master/.github/CODE_OF_CONDUCT.md).

## Setup

Only Java 8 is supported. You can download it from [here](https://adoptopenjdk.net/?variant=openjdk8&jvmVariant=hotspot)
.

#### Download

**Windows:** [GitBash](https://gitforwindows.org/).

**Linux:** Git.

#### Run

```
git clone https://github.com/CreepyOrb924/UnderWare UnderWare
cd UnderWare
```

You will want to replace ```https://github.com/CreepyOrb924/UnderWare``` with the URL of your own fork, which you can
make by clicking [here](https://github.com/CreepyOrb924/UnderWare/fork).

#### Building

After setting up a workspace, you can run the gradle build task from within your IDEA, or you can
run ```./gradlew build``` inside the UnderWare folder.

#### Running Minecraft

The steps are for Intellij, but it should be a similar process for other IDEAs.

1. File -> New -> Project from Existing Sources.
2. Select , then the build.gradle file.
3. In the Gradle tab on the right, expand Run Configurations.
4. Run the following task for your IDEA:
    - Intellij: ```genIntellijRuns```
    - Eclipse: ```genEclipseRuns```
    - VSCode: ```genVSCodeRuns```
5. Press the reimport 🔄 button above.
6. You should see a RUNCLIENT at the top now, you can press the green ▶️ start button to start Minecraft.
7. If you do not see it, you can manually find it inside Gradle -> Tasks -> fg_runs -> runClient.

## Pull Request Process

1. Fork https://github.com/CreepyOrb924/UnderWare and make changes locally to a named branch.
2. Fill out the Pull Request Template (you will see it when making a new pull request) and create a new pull request.
3. Wait for feedback of the developers.
4. Address the feedback of the developers.
5. When the developer gives a green flag, the pull request will be merged.

## Bugs

If you have noticed a bug in UnderWare, please open an issue
at [CreepyOrb924/UnderWare](https://github.com/CreepyOrb924/UnderWare/issues/new).

Please fill out the template and provide enough enformation to recrate the bug.

## Priorities

Our priorities, roughly in order of impact:

* Bug fixes.
* New features.
* Refactorings that make development easier.
* Style fixes.

## Support

Join our [Discord Server](https://discord.gg/pScsnFEyAE).
