[Toc]

# 官网网址

1. [DOM官方说明文档](https://dom.spec.whatwg.org/#introduction-to-dom-events)
2. [Web API接口参考](https://developer.mozilla.org/zh-CN/docs/Web/API/Event/target)

> JS一种编程语言，该语言区分大小写

# 全局对象

- **全局作用域**

  1. 对于开发环境来说：全局作用域是可以被其他作用域可见，并可被其他作用域使用的作用域；
  2. 对于客户端JS来讲：全局作用域通常是执行所有代码的网页。

  ```
  - 定义在函数外的变量都是全局变量，默认为window对象的属性变量，可以使用 window.tetVar获取
  var myvar = 'my value';
  (function() {
    console.log(window.myvar); // my value
  })();
  ```

## 全局变量

> 全局变量实际是全局对象的属性。
>
> 对web页面来说，`window`是全局对象，可以使用`window.variable`获取全局变量。
>
> `window`对象为窗口对象，对当前网页窗口对象；使用`window`对象声明的变量或者函数，在当前窗口有效。如test1.html网页包含test2.html;test2.html网页又包含test3.html网页；在test1.html网页可以使用`window.variable`格式，获取test2.html,test3.html定义的全局变量。也就是说：只要包含在当前窗口显示的网页内的所有定义的全局变量，都可以使用`window.variable`获取。

```
### 1. 定义全局变量：定义在所有函数外。可使用var或者省略变量类型关键符定义
var a="aaa";
c="ccc";
 $(function(){
     console.log(window.a);//aaa
     console.log(window.c);//ccc
 });
 
 ### 2. 直接在window对象添加属性定义全局变量：window.a="sss";
 在任意位置使用：window.a="sss";
 
 
### 使用let和const定义全局变量时,不会成为window的属性
<script>
    var a="aaaa";
    let b="bbbb";
    const c="cccc";
    d="dddd";
    $(function(){
        console.log("a===="+window.a);//a====aaaa
        console.log("b===="+window.b);// b====undefined
        console.log("c===="+window.c);//c====undefined
        console.log("d===="+window.d);//d====dddd
        console.log("==========================");
        console.log("a====@"+a);//a====@aaaa
        console.log("b====@"+b);//b====@bbbb
        console.log("c====@"+c);//c====@cccc
        console.log("d====@"+d);//d====@dddd  
    });
</script> 

let和const定义的变量，会被{}代码块限制，只能在其所属的代码块中使用
if(true){
    var a="aaaa";
    d="dddd";
    let b="bbbb";
    const c="cccc";
}
console.log("a===="+a);//a====aaaa
console.log("d===="+d);//d====dddd  
console.log("b===="+b);// b====Uncaught ReferenceError: b is not defined
console.log("c===="+c);//c====Uncaught ReferenceError: c is not defined
```

## 父子窗口(父子框架)全局变量的获取

- **内联框架获取父框架定义的全局变量**，可以使用`parent.variable`
- **备注**：内联子框架/子窗口是在父窗口加载完成后，才进行加载。

```
### 父框架
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script>
      var a="parent";
      //window.a="parent";
    </script>
</head>
<body>
        <iframe src="./test3.html" width="200" height="200"></iframe>     
</body>
</html>

### 子框架test3.html  js
console.log(parent.a);//parent
```



# 变量

## 变量声明

**JS中有三种类型的变量声明**

- `var`:可声明一个变量，可根据需要进行初始化:使用这种方式声明的变量，不会受代码块`{}`局限
  根据执行内容，可使用`var`声明一个全局/局部的变量。

  ```
  if (true) {
        var x = 5;
      }
      console.log(x);  // x is 5
  ```

- `let`:可声明一个块作用域局部变量，会被代码块`{}`所限定,可根据需要初始化；

  ```
  if (true) {
        let x = 5;
      }
      console.log(x);  // ReferenceError: x is not defined
  ```

- `const`:声明一个块作用域，只读命令常量：必须进行初始化，否则会报错。

- 不使用任何变量关键字定义的变量`x = 42`，这种方式会产生一个未声明式全局变量；JS会显示警告，不建议使用这种方式声明变量。

## 变量值变动

- **使用未初始化变量或者未被声明的变量的情况**
  1. 当使用一个未被初始化的变量时，JS会默认该变量值为`undefined`;
  2. 当使用一个未被声明的变量，会报错

```
var a;
console.log('The value of a is ' + a); // The value of a is undefined

console.log('The value of b is ' + b); // The value of b is undefined
var b;
// This one may puzzle you until you read 'Variable hoisting' below

console.log('The value of c is ' + c); // Uncaught ReferenceError: c is not defined

let x;
console.log('The value of x is ' + x); // The value of x is undefined

console.log('The value of y is ' + y); // Uncaught ReferenceError: y is not defined
let y;
```

- 可以使用`undefined`判断一个变量是否有值

```
var input;
if (input === undefined) {
  console.log("aaaa");
} else {
    console.log("bbbb");
}

//结果显示：aaaa
```

- `undefined`<=> `false`:当应用在布尔运算中时，`undefined`表现为`false`

```
//myArray[0]为undefined,但是在if条件中，其表现行为<=>布尔值false
var myArray = [];
if (!myArray[0]) {
    console.log("aaaa");
}
//结果：aaaa
```

- 在数字运算中，`undefined`转化为`NaN`

```
var a;
a + 2;  // Evaluates to NaN
console.log(a + 2); //NaN
```

- 当变量值=`null`时，在数字运算中，`null`会转化成`0`参与计算；而在布尔运算中，其表现为`false`

```
var n = null;
console.log(n * 32); // 0
if(!n){
    console.log("aaaa");//aaaa
}
```

## 变量作用域

根据作用域范围的不同，可以分为全局变量和局部变量。

- 全局变量：定义在任意函数外的变量，对当前文档的其他代码都可使用的；
- 局部变量：定义在函数内的变量，仅仅在函数内部可用；

JavaScript在 ECMAScript 2015前是没有块语句域的。一个定义在函数内的代码块中的变量，在函数体中，对函数的其他代码都是可见的。也就是说，在函数体内，这个变量是全局变量。

```
function test(){
    if (true) {
      var x = 5;
    }
    console.log(x);  // x is 5
}
```

在ECMAScript 2015后，引入了`let`声明语句，使用`let`声明的变量，其作用范围为块域，即：只会在包含其的代码块中其作用。

```
function test(){
if (true) {
  let y = 5;
}
console.log(y);  // ReferenceError: y is not defined
}
```

## 变量提升

> 变量提升：使用未被声明的变量，而不报错。甚至变量进行了声明和初始化，在使用的时候，变量值也依旧为'undefined'。
>
> 在JS使用`var`定义变量时，会将变量尽可能置于函数顶端;在未正式进行声明并初始化后再使用，其默认初始化值为`undefined`
>
> ```
> /**
>  * Example 1
>  */
> console.log(x === undefined); // true
> var x = 3;
> 
> /**
>  * Example 2
>  */
> // will return a value of undefined
> var myvar = 'my value';
> (function() {
>   console.log(myvar); // undefined
>   var myvar = 'local value';
> })();
> /**
>  * Example 3
>  */
> var x;
> console.log(x === undefined); // true
> x = 3;
> ```

- 使用`let` 和`const`定义的变量，虽然会被提升，但是不会初始化。若未在声明初始化之后使用，会报错。

```
console.log(x); // ReferenceError
let x = 3;
```

# 常量

> 使用`const`可以定义只读常量。
>
> 常量规则和`let`定义的块级变量相同。在同一作用范围内，不能定义与常量名相同的函数或者变量
>
> ```
> // THIS WILL CAUSE AN ERROR :Uncaught SyntaxError: Identifier 'f' has already been declared
> function f() {};
> const f = 5;
> 
> 
> // THIS WILL CAUSE AN ERROR TOO: Uncaught SyntaxError: Identifier 'g' has already been declared
> function f() {
>   const g = 5;
>   var g;
> 
>   //statements
> }
> ```
>
> - **若定义的是常量对象，可修改对象属性值；若定义的是常量数组，可以操作数组：增加或修改数组元素**
>
>   > 也就是说，当`const`修饰数据类型为对象时，其对象内的属性元素不再受到保护，可以进行修改。
>
> ```
>  //const定义对象时
>  const MY_OBJECT = {'key': 'value'};
>  MY_OBJECT.key = 'otherValue';
>  console.log(MY_OBJECT.key); //otherValue
>  
>  ### const 定义数组时
> const MY_ARRAY = ['HTML','CSS'];
> MY_ARRAY[0]='html';//修改数组下标为0的值
> MY_ARRAY.push('JAVASCRIPT');
> console.log(MY_ARRAY); //logs ['html','CSS','JAVASCRIPT'];
> ```

# 数据类型

> 最新的ECMAScript 标准中，有八种数据类型，其中7种基本数据类型，一种对象数据类型。

- **7种基本数据类型**
  - [Boolean](https://developer.mozilla.org/en-US/docs/Glossary/Boolean)：`true`||`false`
  - [null](https://developer.mozilla.org/en-US/docs/Glossary/Null):表示空值的特殊关键字，与`Null`, `NULL`不等价，因为JS是区分大小写的；
  - [undefined](https://developer.mozilla.org/en-US/docs/Glossary/undefined)：一种未定义值的顶级属性
  - [Number](https://developer.mozilla.org/en-US/docs/Glossary/Number):整型或浮点型。For example: `42` or `3.14159`.
  - [BigInt](https://developer.mozilla.org/en-US/docs/Glossary/BigInt):具有任意精度的整型。 For example: `9007199254740992n`
  - [String](https://developer.mozilla.org/en-US/docs/Glossary/String)：表示文本值的字符序列。如："Hello";
  - [Symbol](https://developer.mozilla.org/en-US/docs/Glossary/Symbol):(new in ECMAScript 2015)一种实例唯一且不可变的数据类型
- **对象数据类型**
  - [Object](https://developer.mozilla.org/en-US/docs/Glossary/Object):对象和函数在JS中有着重要作用，可以将对象看作多个值的命名容器，而函数为脚本执行步骤。

## 数据类型转换

> JS是一种动态类型语言，不需要在声明变量的时候，声明数据类型。数据类型在脚本执行过程中会根据需要自动进行转换。
>
> ```
> //如下：answer初始声明为数字类型，随后赋值字符串，脚本不会报错，正常执行。
> var answer = 42;
> answer="HelloWorld";
> console.log(answer);
> ```

### 数字类型和字符串类型

- **使用`+`串联数值与字符串的情况**

```
### 当使用+将字符串和数字串联起来时，其值为字符串
x = 'The answer is ' + 42 // "The answer is 42"
y = 42 + ' is the answer' // "42 is the answer"

### 当操作不为 + ,而是其他操作符，如：-，*，/,>等，且操作符俩端一个为数字的字符串，一个为数字时，其不会按照字符进行串联，而是全都转换为数字，然后根据操作符进行计算。
'37' - 7 // 30
'37' + 7 // "377"

```

- **字符串转为数字**

```
parseInt(string)
parseInt(string, radix) //radix：数学数制，范围：2-36;radix默认不是10进制，会根据数字类型自动识别
其转换后的值为：数字字符串的值或者NaN。
若string为小数，会只返回整数部分

parseFloat(string) 
其结果为NaN，或者是小数，或者是BigInt
```

- **使用一元`+`操作数字字符串**

```
'1.1' + '1.1' // '1.11.1'
(+'1.1') + (+'1.1') // 2.2
// Note: the parentheses are added for clarity, not required.
```

## 字符串&日期转换

- 使用`moment`插件实现字符串与日期转换

```
let tmpTime = "2022-01-01 23:32:12";
let startTime = moment(tmpTime, 'YYYY-MM-DD HH:mm:ss');//转换为moment对象
let tStartTime = startTime.format('YYYY-MM-DD') + ' 00:00:00'; //将moment对象转为指定格式
```



# 控制流和异常处理

## 块语句

> 使用一对大括号包含的语句

```
{
  statement_1;
  statement_2;
  ⋮
  statement_n;
}
```

## 条件语句

### [`if...else`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Guide/Control_flow_and_error_handling#if...else_statement)

```
if (condition_1) {
  statement_1;
} else if (condition_2) {
  statement_2;
} else if (condition_n) {
  statement_n;
} else {
  statement_last;
}
```

- **假值**:以下值在布尔判断中，会被判断为`false`.除了以下值外，其他所有值，包括对象，在条件语句的判断语句中会被判定为`true`

```
false
undefined
null
0
NaN
the empty string ("")
```

```
### 注意区分基本数据类型的布尔类型，和布尔对象类型Boolean
var b = new Boolean(false);
if (b)         // this condition evaluates to true
if (b == true) // this condition evaluates to false
```

### [`switch`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Guide/Control_flow_and_error_handling#switch_statement)

```
switch (expression) {
  case label_1:
    statements_1
    [break;]
  case label_2:
    statements_2
    [break;]
    …
  default:
    statements_def
    [break;]
}
```

## 异常处理

- **异常类型**

  > - [ECMAScript exceptions](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Error#error_types)
  > - [`DOMException`](https://developer.mozilla.org/en-US/docs/Web/API/DOMException) and [`DOMError`](https://developer.mozilla.org/en-US/docs/Web/API/DOMError)

### [`throw` statement](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Guide/Control_flow_and_error_handling#throw_statement)

```
throw 'Error2';   // String type
throw 42;         // Number type
throw true;       // Boolean type
throw {toString: function() { return "I'm an object!"; } };
```

### [`try...catch`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Guide/Control_flow_and_error_handling#try...catch_statement)

```
function getMonthName(mo) {
  mo = mo - 1; // Adjust month number for array index (1 = Jan, 12 = Dec)
  let months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul',
                'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
  if (months[mo]) {
    return months[mo];
  } else {
    throw 'InvalidMonthNo'; // throw keyword is used here
  }
}

try { // statements to try
  monthName = getMonthName(myMonth); // function could throw exception
}
catch (e) {
  monthName = 'unknown';
  logMyErrors(e); // pass exception object to error handler (i.e. your own function)
}
```

## 循环&迭代

## [`for`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Guide/Loops_and_iteration#for_statement)

```
for ([initialExpression]; [conditionExpression]; [incrementExpression])
  statement
```

## [`do...while`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Guide/Loops_and_iteration#do...while_statement)

```
do
  statement //首先执行
while (condition);
```

## [`while`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Guide/Loops_and_iteration#while_statement)

```
while (condition)
  statement
```

## [`labeled`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Guide/Loops_and_iteration#labeled_statement)

> 标签语句。可以标签，以便在程序的其他地方引用。
>
> ```
> let x = 0;
> let z = 0;
> labelCancelLoops: while (true) {
>   console.log('Outer loops: ' + x);
>   x += 1;
>   z = 1;
>   while (true) {
>     console.log('Inner loops: ' + z);
>     z += 1;
>     if (z === 10 && x === 10) {
>       break labelCancelLoops;   //停止labelCancelLoops循环
>     } else if (z === 10) {
>       break;
>     }
>   }
> }
> ```

## [`break`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Guide/Loops_and_iteration#break_statement)

> 中断当前循环，或者中断`switch`条件轮询

```
break;  //中断当前循环操作||条件查询
break [label]; //中断指定label循环
```

## [`continue`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Guide/Loops_and_iteration#continue_statement)

```
continue [label]; //跳过本次循环，执行下一次循环。若指定label，则跳过指定label的一次循环。
```

## [`for...in`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Guide/Loops_and_iteration#for...in_statement)

> 最好不用`for..in`遍历对象数组，在使用`for...in`遍历的时候，除了返回索引还会返回用户定义属性名称
>
> ```
>  const arr = [3, 5, 7];
>  arr.foo = 'hello';
> 
> for (let i in arr) {
> console.log(i); // logs "0", "1", "2", "foo"
> }
> ```
>
> 

```
//迭代查询所有对象的属性
for (variable in object)
  statement
```

```
var testObj={"name":"sss","age":14}
for(let i in testObj){
	console.log(i+"===="+testObj[i]);
}
输出结果：
name====sss
age====14


function dump_props(obj, obj_name) {
  let result = '';
  for (let i in obj) {
    result += obj_name + '.' + i + ' = ' + obj[i] + '<br>';
  }
  result += '<hr>';
  return result;
}
```

## [`for...of`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Guide/Loops_and_iteration#for...of_statement)

> 遍历迭代对象,包括([`Array`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array), [`Map`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Map), [`Set`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Set), [`arguments`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Functions/arguments))

```
for (variable of object)
  statement
```

```
const arr = [3, 5, 7];
arr.foo = 'hello';

for (let i in arr) {
   console.log(i); // logs "0", "1", "2", "foo"
}

for (let i of arr) {
   console.log(i); // logs 3, 5, 7
}
```

# 函数

## 函数提升

> 函数提升，只提升函数声明，不提升函数表达式。
> 说明：函数可以先使用，在声明；而函数表达式只能先声明后，才能使用。

```
/* Function declaration */

foo(); // "bar"

function foo() {
  console.log('bar');
}

/* Function expression */

baz(); // TypeError: baz is not a function

var baz = function() {
  console.log('bar2');
};
```

## 函数声明&函数表达式

- **函数声明**

  ```
  function square(number) {
    return number * number;
  }
  ```

- **函数表达式**

  > 函数表达式可以方便的作为参数进行传递

```
const square = function(number) { return number * number }  //函数表达式
var x = square(4) // x gets the value 16

### 函数表达式，在指定条件下声明
var myFunc;
if (num === 0) {
  myFunc = function(theObject) {
    theObject.make = 'Toyota';
  }
}
```

​       **函数表达式作为参数传递**

```
function map(f, a) {
  let result = []; // Create a new Array
  let i; // Declare variable
  for (i = 0; i != a.length; i++)
    result[i] = f(a[i]);
  return result;
}
const f = function(x) {
   return x * x * x;
}
let numbers = [0, 1, 2, 5, 10];
let cube = map(f,numbers);
console.log(cube);
```

## 函数调用

> 有三种方式调用已定义的函数：
> 如定义函数：
>
> ```
> var foo = function bar() {
>    // statements go here
> }
> ```
>
> 1. 函数名调用：`bar();`
> 2. `arguments.callee()`    :`arguments.callee`包含当前执行函数，也可用来作为匿名函数的调用；ECMAScript (ES5)中被禁止使用
> 3. 使用指定函数的变量：`foo()`

```
### 一般函数调用方式
square(5);

### 函数自调用
function factorial(n) {
  if ((n === 0) || (n === 1))
    return 1;
  else
    return (n * factorial(n - 1));
}
```

## 函数作用域

1. 定义在全局作用域的函数，可以使用所有定义在全局作用域的变量或者函数；

2. 定义在函数内部的变量，无法被其他外部函数使用,函数可以访问定义在其作用域内的所有变量和函数；

3. 子函数可以获取所有定义在父函数的变量以及所有能被父函数所能访问的变量

   ```
   // The following variables are defined in the global scope
   var num1 = 20,
       num2 = 3,
       name = 'Chamakh';
   
   // This function is defined in the global scope
   function multiply() {
     return num1 * num2;
   }
   
   multiply(); // Returns 60
   
   // A nested function example
   function getScore() {
     var num1 = 2,
         num2 = 3;
     function add() {
       return name + ' scored ' + (num1 + num2);
     }
     return add();
   }
   getScore(); // Returns "Chamakh scored 5"
   ```

   ## 递归函数

   > 一个函数调用自身叫做递归函数
   
   # 引用赋值&传值赋值
   
   > 在使用`=`进行赋值时，当`=`右侧的对象为JS基本数据数据类型时，为值传递；
   >
   > 当`=`右侧为对象时，传递给目标变量的是：对象的引用地址

# 参考资料

1. https://developer.mozilla.org/en-US/docs/Web/JavaScript/Guide/Grammar_and_types#variable_hoisting
2. https://developer.mozilla.org/en-US/docs/Web/JavaScript/Guide/Grammar_and_types#variable_scope