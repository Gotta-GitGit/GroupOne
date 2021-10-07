$(function () {
    let bodyLen = $('#item').children().length     //寫入商品資料
    console.log('b;'+bodyLen)
    for (let i = 0; i < bodyLen; i++) {
        let pnv = $('.productName').eq(i).val()
        let produveName = $('.productName').eq(i).next()
        produveName.text(pnv)
        let prv = $('.price').eq(i).val()
        let price = $('.price').eq(i).next()
        price.text(prv)
    }
    function sumC() {  //金額計算
        let sum = 0     
        // let itemLen = $('#item').children.length
        let itemLen = document.getElementById('item').children.length
        for (let i = 0; i < itemLen; i++) {
            let priceV = $('.price').eq(i).val()
            let numberV = $('.number').eq(i).val()
            sum = sum + (priceV * numberV)
        }
        let pt = sum / 100
        $('#amount').val(sum)
        $('#point').val(pt)
        let amount = $('#amount').val();
        let point = $('#point').val()
        $('#amount').next().text(amount)
        $('#point').next().text(point)
    }
    window.onload = sumC()

   
    let befV
    $('.number').focus(function () {  //數量控制
        befV = $(this).val()
    }).change(function () {
        let numV = $(this).val()
        if (numV < "0" || numV > "9") {
            $(this).val(befV)
        } else if (numV == 0) {
            $(this).val(1)
        }
        sumC()
    })
    $('tbody').on('click', '.minus', function () {   //數量減少
        let nv = Number($(this).next().val())
        let afretnv = nv -1
        if (afretnv<=0){
            afretnv =1
        }
        $(this).next().val(afretnv)
        sumC()
    })
    $('tbody').on('click', '.plus', function () {    //數量增加
        let nv = Number($(this).prev().val())
        $(this).prev().val(nv + 1)
        sumC()
    })
    $('tbody').on('click', '.delete', function () { //刪除
        $(this).closest('tr').remove()                
        let c =$('#item').children.length
        console.log("jquery:"+c)
        let a = document.getElementById('item')
        let b = a.children.length
        console.log('JS:'+b)
        if (a.children.length == 0) {
            $('#amount').val(0)
            $('#point').val(0)
            $('#amount').next().text(0)
            $('#point').next().text(0)
        } else {
            sumC()
        }
    })
    $('#updata').on('click', function () { //訂單確認
        // document.forms[0].submit()
        $('#pay').attr('disabled', false)
        $('#updata').attr('disabled', true)
        $('.delete').attr('disabled', true)
        $('.number').attr('readonly', true)
        $(".minus").attr('disabled', true)
        $('.plus').attr('disabled', true)
    })
    $('#pay').on('click', function () { //付款
        document.forms['shoppingCarts'].submit()
    })
    $('tbody').on('click', '.delete', function () {
        let noList = $('#item').children().length
        console.log(noList)
        if (noList == 0) {
        	$('#pay').text('確定刪除')
        	console.log(noList)
            
        }
    })
    $('tbody').on('click', '.delete', function () {
        let noList = $('#item').children().length
        console.log(noList)
        if (noList == 0) {
            Swal.fire({
                title: 'Are you sure?',
                text: "清空購物車",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: '確定',
                cancelButtonText : '取消'
            }).then((result) => {
                if (result.isConfirmed) {
                    Swal.fire(
                        '成功!',
                        '目前無購買項目',
                        'success'
                    )
                    document.forms['shoppingCarts'].submit()
                }
                else{
                    location.reload()
                }
            })
        }
        })
})