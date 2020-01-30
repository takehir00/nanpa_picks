# Nanpa_Picks

## 本アプリケーションの概要
トップページにはネット上にあるナンパのノウハウ関連の記事が一覧表示される。  
利用者はその記事にコメントをし、記事について他のユーザーと語り合うことができる。

## 機能一覧
### クライアントサイド 
 - 記事一覧画面表示  
 - 記事詳細画面表示
 - 記事に対するコメント投稿機能
 - 記事に対するコメント一覧機能
 - ユーザー作成機能
 - ユーザー詳細画面表示
 - ユーザーがコメントした記事一覧機能
 - ログイン、ログアウト機能
 - ページネーション
 - フォームにトークンを埋め込むことによるCSRF対策
### 管理画面サイド
 - 記事の一覧、作成、更新、削除機能
 - コメントの一覧、作成、更新、削除機能
 - ユーザーの一覧、作成、更新、削除機能
 - ログイン、ログアウト機能
 - ページネーション
 - フォームにトークンを埋め込むことによるCSRF対策

## 使用技術
- サーバーサイド
    - 言語: Java8
    - フレームワーク: Spring Boot 2
        - セキュリティ周り: Spring Security
        - DB操作: Java Persistence API
    - DB: MySQL
 - フロントエンド
    - HTML,CSS
    - Bootstrap
    
## プロジェクト構成
マルチプロジェクト構成にしている。  
クライアントが利用する機能はclientモジュール。  
管理者が利用する機能はadminモジュール。  
各モジュールから共通で利用されるDB周りの機能はdbモジュール。

clientモジュールとadminモジュールは別サーバーにデプロイする。
adminモジュールサーバーはip制限をかけてセキュリティ面を考慮。

## インフラ構成
- アプリケーションを起動するサーバーはAWSのEC2
- DBサーバーはAWSのRDS
