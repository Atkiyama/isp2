# DBLover

##
注意事項
本アプリケーションは現環境では動作しないのでご注意ください

## このアプリケーションについて

CSVファイルのような表データを作成,整理できるようなデータベースをWebアプリケーションとして実装したアプリケーションになります。


## 使用言語・環境について

使用言語は以下のものです。

- OS:MAC OS(HighSierra)
- 開発言語:Java(Spring boot) JavaScript

## 　機能について

主に以下の機能があります
- Webアプリケーション上でデータを整理する
- 作成したデータや保存したデータを管理する


## 環境構築方法
各バージョンを以下に揃える必要があります。
- java:11



##### 1 HomeBrewのインストール

以下のコマンドでまずは[Homebrew](https://brew.sh/index_ja)をインストールしておいてください

``` sh : grepVer3.sh

/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
```

##### 2 javaのインストール

以下のコマンドでjava11をインストールしてください。
``` sh : grepVer3.sh
brew install java11
```
指示があればインストールのメッセージにある通り環境変数を設定してください

``` sh : grepVer3.sh
If you need to have openjdk@11 first in your PATH run:
  echo 'export PATH="/usr/local/opt/openjdk@11/bin:$PATH"' >> ~/.zshrc

For compilers to find openjdk@11 you may need to set:
  export CPPFLAGS="-I/usr/local/opt/openjdk@11/include"

% echo 'export PATH="/usr/local/opt/openjdk@11/bin:$PATH"' >> ~/.zshrc
% export CPPFLAGS="-I/usr/local/opt/openjdk@11/include"
% source ~/.zshrc

```
#### 実行方法

*現在整備中です

コンパイル後srcディレクトリに移動以下のコマンドでサーバーを立てます
``` sh : grepVer3.sh
rm WebContent/WEB-INF/classes/server/*
rm WebContent/WEB-INF/classes/project/dbLover/*
rm bin/server/*         
rm bin/project/dbLover/*
javac -d bin -cp "lib/*" src/project/dbLover/*.java
javac -d bin -cp "lib/*" src/server/*.java
javac -d WebContent/WEB-INF/classes -cp "lib/*" src/project/dbLover/*.java    
java -cp "lib/*:bin" server.AppServer 8080 /isp2 WebContent
```
その後 [http://localhost:8080/isp2/project/dbLover/top.html](http://localhost:8080/isp2/project/dbLover/top.html) にアクセスして下さい
