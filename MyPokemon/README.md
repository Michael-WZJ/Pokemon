# Pokemon
Pokemon

### 运行jar包
java -jar MyPokemon-0.0.1-SNAPSHOT.jar
（需要借助Maven打包插件）


### Api Reference
| Urls Ⅰ       | Urls Ⅱ      | Methods               | Actions   |
|:-------------|:------------|-----------------------|-----------|
| /web/mypokes | /           | GET                   | 获取所有宝可梦信息 |
|              | /{pokeCode} |                       | 获取单个宝可梦信息 |
|              | /count      |                       | 获取所有宝可梦数量 |
|              | /conditions | POST                  | 条件查询      |
|              | /           | POST                  |           |
|              | /           | PUT                   |           |
|              | /{pokeCode} | DELETE                |           |
|              |             |                       |           |
| /web/users   | /           | GET                   |           |
|              |             |                       |           |
|              |             | /raw_data/:_datatype_ |           |
|              |             | ?projectid=xxx        |           |
|              |             |                       |           |

### 其他