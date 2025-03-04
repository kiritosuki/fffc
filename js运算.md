在 JavaScript 中，整除和取余运算可以通过以下两种方式实现：

### 1. 取余运算（模运算）

取余运算使用 `%` 运算符，它返回除法运算后的余数。

```
javascript

let result = 10 % 3;  // 结果是 1，因为 10 除以 3，商为 3，余数为 1
console.log(result);  // 输出 1
```

### 2. 整除（向下取整）

JavaScript 没有直接的整除运算符，但可以通过结合使用 `/` 运算符和 `Math.floor()` 来实现整除。`Math.floor()` 会返回小于或等于给定数字的最大整数。

```
javascript

let result = Math.floor(10 / 3);  // 结果是 3，因为 10 除以 3，商为 3.33，取整后是 3
console.log(result);  // 输出 3
```

### 3. 其他方式

如果你希望处理负数时整除的结果为更接近零的整数，可以使用 `Math.trunc()`，它会移除小数部分，返回接近零的整数。

```
javascript

let result = Math.trunc(-10 / 3);  // 结果是 -3，因为 -10 除以 3，商为 -3.33，去除小数后是 -3
console.log(result);  // 输出 -3
```

这就是 JavaScript 中的整除和取余运算的基本用法。