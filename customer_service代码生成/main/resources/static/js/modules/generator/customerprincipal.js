$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'customerprincipal/list',
        datatype: "json",
        colModel: [			
			{ label: 'cId', name: 'cId', index: 'c_id', width: 50, key: true },
			{ label: '账号对应机构id', name: 'orgId', index: 'org_id', width: 80 }, 			
			{ label: '是否为主账号：0否，1是', name: 'isParent', index: 'is_parent', width: 80 }, 			
			{ label: '客户账号', name: 'cAccount', index: 'c_account', width: 80 }, 			
			{ label: '客户密码', name: 'cPassword', index: 'c_password', width: 80 }, 			
			{ label: '账号状态：0 正常，1锁定', name: 'state', index: 'state', width: 80 }, 			
			{ label: '', name: 'lastPasswordResetDate', index: 'last_password_reset_date', width: 80 }, 			
			{ label: '创建时间', name: 'createTime', index: 'create_time', width: 80 }			
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		customerPrincipal: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.customerPrincipal = {};
		},
		update: function (event) {
			var cId = getSelectedRow();
			if(cId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(cId)
		},
		saveOrUpdate: function (event) {
			var url = vm.customerPrincipal.cId == null ? "customerprincipal/save" : "customerprincipal/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.customerPrincipal),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var cIds = getSelectedRows();
			if(cIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "customerprincipal/delete",
                    contentType: "application/json",
				    data: JSON.stringify(cIds),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(cId){
			$.get(baseURL + "customerprincipal/info/"+cId, function(r){
                vm.customerPrincipal = r.customerPrincipal;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});