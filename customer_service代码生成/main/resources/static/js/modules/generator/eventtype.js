$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'eventtype/list',
        datatype: "json",
        colModel: [			
			{ label: 'eventTypeId', name: 'eventTypeId', index: 'event_type_id', width: 50, key: true },
			{ label: '活动类别名称', name: 'typeName', index: 'type_name', width: 80 }, 			
			{ label: '', name: 'typeDesc', index: 'type_desc', width: 80 }, 			
			{ label: '', name: 'orgId', index: 'org_id', width: 80 }			
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
		eventType: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.eventType = {};
		},
		update: function (event) {
			var eventTypeId = getSelectedRow();
			if(eventTypeId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(eventTypeId)
		},
		saveOrUpdate: function (event) {
			var url = vm.eventType.eventTypeId == null ? "eventtype/save" : "eventtype/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.eventType),
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
			var eventTypeIds = getSelectedRows();
			if(eventTypeIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "eventtype/delete",
                    contentType: "application/json",
				    data: JSON.stringify(eventTypeIds),
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
		getInfo: function(eventTypeId){
			$.get(baseURL + "eventtype/info/"+eventTypeId, function(r){
                vm.eventType = r.eventType;
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