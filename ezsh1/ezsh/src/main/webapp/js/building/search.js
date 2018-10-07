$("#search").click(
		function() {
			// 查询条件选项
			var conditions = $("#conditions").val();
			// 查询关键字
			var keyword = $("#keyword").val();
			var managerId = $('#buildManagerId').combobox('getValue');
			$.ajax({
				type : "POST",
				url : "/ezsh/build/setConditions",
				data : "conditions=" + conditions + "&keyword=" + keyword
						+ "&managerId=" + managerId,
				dataType : "json",
				success : function(building) {
					if (building.buildId == null) {
						building.buildId = "";
					}
					if (managerId == null) {
						managerId = "";
					}
					if (building.buildName == null) {
						building.buildName = "";
					}
					if (building.buildAddr == null) {
						building.buildAddr = "";
					}
					if (building.buildType == null) {
						building.buildType = "";
					}
					if (building.remark == null) {
						building.remark = "";
					}
					$('#dg').datagrid('load', {
						buildManagerId : managerId,
						buildId : building.buildId,
						buildName : building.buildName,
						buildAddr : building.buildAddr,
						buildType : building.buildType,
						remark : building.remark,
					});
				}
			});
		});