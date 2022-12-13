# DBLover



## このアプリケーションについて

CSVファイルのような表データを作成,整理できるようなデータベースをWebアプリケーションとして実装したアプリケーションになります。


## 使用言語・環境について

使用言語は以下のものです。

- OS:MAC OS(Moneterey 12.6.1)
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


