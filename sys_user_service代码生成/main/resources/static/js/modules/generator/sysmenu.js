$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sysmenu/list',
        datatype: "json",
        colModel: [			
			{ label: 'menuId', name: 'menuId', index: 'menu_id', width: 50, key: true },
			{ label: '父菜单ID，一级菜单为0', name: 'parentId', index: 'parent_id', width: 80 }, 			
			{ label: '菜单名称', name: 'name', index: 'name', width: 80 }, 			
			{ label: '菜单URL', name: 'path', index: 'path', width: 80 }, 			
			{ label: '路由是否隐藏：1是，0否', name: 'hidden', index: 'hidden', width: 80 }, 			
			{ label: '没有展开选项:0否，1是', name: 'noDropdown', index: 'no_dropdown', width: 80 }, 			
			{ label: '组件名称', name: 'component', index: 'component', width: 80 }, 			
			{ label: '类型   0：目录   1：菜单   2：按钮', name: 'type', index: 'type', width: 80 }, 			
			{ label: '菜单图标', name: 'icon', index: 'icon', width: 80 }, 			
			{ label: '排序', name: 'orderNum', index: 'order_num', width: 80 }			
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
		sysMenu: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.sysMenu = {};
		},
		update: function (event) {
			var menuId = getSelectedRow();
			if(menuId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(menuId)
		},
		saveOrUpdate: function (event) {
			var url = vm.sysMenu.menuId == null ? "sysmenu/save" : "sysmenu/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.sysMenu),
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
			var menuIds = getSelectedRows();
			if(menuIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "sysmenu/delete",
                    contentType: "application/json",
				    data: JSON.stringify(menuIds),
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
		getInfo: function(menuId){
			$.get(baseURL + "sysmenu/info/"+menuId, function(r){
                vm.sysMenu = r.sysMenu;
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