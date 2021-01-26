lombok是通过反射利用Java语言在编译时将需要的方法写入到类中的。容易带来一些[问题](https://mp.weixin.qq.com/s?__biz=MzIzMTczODU2Mg==&mid=2247487777&idx=2&sn=e4c12a7ee7a3a223cff04eeec50dd2d2&chksm=e89ecc8cdfe9459a415257ca3e3cd499e97eb2ab9caf859631048a3bb709ac5ca7af468f333f&scene=126&sessionid=1607484011&key=985be40c305f941f1d4fdd2a3c19bfeaf109f4e7199c0f0b7b324b5616e092cc65c80a51d0d4c9ef0bcea8ccb48df62e3771b574ff1701525b8dff5d8bb520010bd76b73c68d88b217b491f15a44b7470ef092e53a4137a85188211d075698248de5189501fe5f68f1fb3a2025cda31bee722376a3b77f03025721d9f058864a&ascene=1&uin=MzA0MTI5MjgwNA%3D%3D&devicetype=Windows+10+x64&version=63000039&lang=zh_CN&exportkey=AaxdmfGME70tJCTTnFJQBDA%3D&pass_ticket=sjeij3u8zfl01hnbyUzkpS8tGu0FruS1a%2BvwMnZI4s1yDQn%2BdbkrE%2FqtgdVKTQWJ&wx_header=0&fontgear=2)
。  
需要严格规范lombok使用,只能使用以下注解,如需要使用其他注解需要一起讨论确定不会带来严重隐患。
- @Slf4j
- @Setter
- @Getter
- @ToString
- @Accessors(chain = true)
- @NoArgsConstructor
- @AllArgsConstructor

https://blog.csdn.net/vbirdbest/article/details/79495398