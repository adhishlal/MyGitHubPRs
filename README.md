# MyGitHubPRs

<img width="319" alt="demo" src="https://user-images.githubusercontent.com/110488747/183308531-10878ff1-80a2-4ec2-a76e-2700de8264b0.png">

APK Link : https://drive.google.com/drive/folders/1NONKl9Qr-siTgZ_c5Zc6mK9PJz6YrkRW?usp=sharing

App Demo Video Link: https://drive.google.com/file/d/1RaN3ecqV79HCcM1CXTxd-uTAgxI55MZe/view?usp=sharing

# Here is few pointers for this application:

1. Architecture used: MVVM with Repository Pattern
2. Dependency injection used: Dagger2
3. Architected using Kotlin DSL
4. Edge cases handled:
- Unauthenticated user
- Any public repository access and it's closed pull requests
- Choice to navigate view PR according to repository
- Internet check
- Important note - Pushing of actual GitHub Auth Token is restricted hence if you want to clone this repository, you need to add your person token to BuildSrc's Dependency file.
