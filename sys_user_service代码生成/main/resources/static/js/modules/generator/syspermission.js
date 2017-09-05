$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'syspermission/list',
        datatype: "json",
        colModel: [			
			{ label: 'permId', name: 'permId', index: 'perm_id', width: 50, key: true },
			{ label: '请求url的前缀', name: 'urlPrefix', index: 'url_prefix', width: 80 }, 			
			{ label: '权限名称', name: 'permName', index: 'perm_name', width: 80 }, 			
			{ label: '权限描述', name: 'permDesc', index: 'perm_desc', width: 80 }			
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
		sysPermission: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.sysPermission = {};
		},
		update: function (event) {
			var permId = getSelectedRow();
			if(permId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(permId)
		},
		saveOrUpdate: function (event) {
			var url = vm.sysPermission.permId == null ? "syspermission/save" : "syspermission/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.sysPermission),
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
			var permIds = getSelectedRows();
			if(permIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "syspermission/delete",
                    contentType: "application/json",
				    data: JSON.stringify(permIds),
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
		getInfo: function(permId){
			$.get(baseURL + "syspermission/info/"+permId, function(r){
                vm.sysPermission = r.sysPermission;
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